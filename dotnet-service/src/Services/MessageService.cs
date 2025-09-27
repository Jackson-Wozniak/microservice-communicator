using dotnet_service.Client;
using dotnet_service.Data;
using dotnet_service.DTOs;
using dotnet_service.Entities;
using dotnet_service.Enums;

namespace dotnet_service.Services;

public class MessageService : IMessageService
{
    private readonly MessageDbContext _messageDbContext;

    public MessageService(MessageDbContext m)
    {
        _messageDbContext = m;
    }

    public IEnumerable<Message> GetMessages()
    {
        return _messageDbContext.Messages;
    }
    
    public void StartConversation(string conversationName)
    {
        MessageHttpClient.Send(new Message
        {
            Source = SourceType.DotnetService,
            Conversation = conversationName,
            MessageNumber = 1,
            Timestamp = DateTime.Now
        });
    }

    public async Task<bool> ReceiveAndQueueNextMessage(MessageDTO lastMessage)
    {
        _messageDbContext.Messages.Add(lastMessage.ToMessage());
        await _messageDbContext.SaveChangesAsync();

        await Task.Delay(10000);
        
        MessageHttpClient.Send(new Message
        {
            Source = SourceType.DotnetService,
            Conversation = lastMessage.Conversation,
            MessageNumber = lastMessage.MessageNumber + 1,
            Timestamp = DateTime.Now
        });
        return true;
    }
}