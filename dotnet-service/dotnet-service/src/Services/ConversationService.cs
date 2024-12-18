using dotnet_service.Data;
using dotnet_service.Entities;

namespace dotnet_service.Services;

public class ConversationService : IConversationService
{
    private readonly ConversationDbContext _conversationDbContext;

    public ConversationService(ConversationDbContext dbContext)
    {
        _conversationDbContext = dbContext;
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

    public void ClearAndBeginConversation(string name)
    {
        using var transaction = _conversationDbContext.Database.BeginTransaction();
        try
        {
            var conversation = _conversationDbContext.Conversations.Find(name);
            if (conversation != null)
            {
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
}