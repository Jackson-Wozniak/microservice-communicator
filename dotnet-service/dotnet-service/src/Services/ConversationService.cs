﻿using dotnet_service.Data;
using dotnet_service.Dtos;
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

    public void BeginConversation(string name)
    {
        if (_conversationDbContext.Conversations.Find(name) != null)
        {
            //throw exception
        }
        var conversation = new Conversation(name, DateTime.Now);

        _conversationDbContext.Conversations.Add(conversation);
        _conversationDbContext.SaveChanges();
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

    public void ReceiveMessage(MessageDto request)
    {
        var conversation = _conversationDbContext.Conversations.Find(request.ConversationName);
        if (conversation == null)
        {
            return;
            //throw exception
        }

        var message = new Message(request.MessageId, conversation, 
            request.SentAt, DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss"));
        
        conversation.Messages.Add(message);

        _conversationDbContext.Messages.Add(message);
        _conversationDbContext.Conversations.Add(conversation);

        _conversationDbContext.SaveChanges();
        
        //now wait 10 seconds and then send a new message back to sender
    }
}