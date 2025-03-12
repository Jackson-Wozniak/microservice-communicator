using dotnet_service.Clients;
using dotnet_service.Data;
using dotnet_service.Dtos;
using dotnet_service.Entities;
using Microsoft.EntityFrameworkCore;

namespace dotnet_service.Services;

public class ConversationService : IConversationService
{
    private readonly ConversationDbContext _conversationDbContext;
    private readonly SpringBootHttpClient _springBootHttpClient;

    public ConversationService(ConversationDbContext dbContext, SpringBootHttpClient httpClient)
    {
        _conversationDbContext = dbContext;
        _springBootHttpClient = httpClient;
    }

    public List<Conversation> FindAllConversations()
    {
        return _conversationDbContext.Conversations.ToList();
    }
    
    public async Task<Conversation> FindConversationByName(string name)
    {
        var conversation = await _conversationDbContext.Conversations.FindAsync(name);

        if (conversation == null)
        {
            //throw exception
        }

        return conversation;
    }

    public void BeginConversation(string name)
    {
        var conversation = _conversationDbContext.Conversations.Find(name);
        if (conversation != null)
        {
            _conversationDbContext.Conversations.Remove(conversation);
            _conversationDbContext.SaveChanges();
            //throw exception
        }
        conversation = new Conversation(name, DateTime.Now);

        _conversationDbContext.Conversations.Add(conversation);
        _conversationDbContext.SaveChanges();
    }

    public void ClearAndBeginConversation(string name)
    {
        using var transaction = _conversationDbContext.Database.BeginTransaction();
        try
        {
            var conversation = _conversationDbContext.Conversations
                .Include(c => c.Messages) // Ensure messages are loaded
                .FirstOrDefault(c => c.Name.Equals(name));
            if (conversation != null)
            {
                _conversationDbContext.Messages.RemoveRange(conversation.Messages);
                _conversationDbContext.Conversations.Remove(conversation);
                _conversationDbContext.SaveChanges();
            }

            conversation = new Conversation(name, DateTime.Now);

            _conversationDbContext.Conversations.Add(conversation);
            _conversationDbContext.SaveChanges();
            transaction.Commit();
        }
        catch (Exception)
        {
            transaction.Rollback();
            //throw exception
        }
    }

    public void ReceiveMessage(MessageDto request)
    {
        var conversation = _conversationDbContext.Conversations.Find(request.ConversationName);
        if (conversation == null)
        {
            conversation = new Conversation(request.ConversationName, DateTime.Now);
            _conversationDbContext.Conversations.Add(conversation);
            _conversationDbContext.SaveChanges();
            //TODO: throw exception
        }

        var message = new Message(request.MessageId, conversation, 
            request.SentAt, DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss"));
        
        conversation.Messages.Add(message);
        _conversationDbContext.Conversations.Update(conversation);
        _conversationDbContext.Messages.Add(message);
        _conversationDbContext.SaveChanges();
        
        //now wait 10 seconds and then send a new message back to sender
        Task.Run(() =>
        {
            Thread.Sleep(10000);
            _springBootHttpClient.SendNextMessage(message);
        });
    }
}