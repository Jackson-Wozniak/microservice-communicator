using dotnet_service.Entities;
using dotnet_service.Enums;

namespace dotnet_service.DTOs;

public class MessageDTO
{
    public string Source { get; set; }
    public string Conversation { get; set; }
    public long MessageNumber { get; set; }
    public DateTime Timestamp { get; set; }
    
    public MessageDTO(Message message)
    {
        Source = message.Source.ToName();
        Conversation = message.Conversation;
        MessageNumber = message.MessageNumber;
        Timestamp = message.Timestamp;
    }

    public Message ToMessage()
    {
        return new Message
        {
            MessageNumber = MessageNumber,
            Conversation = Conversation,
            Source = SourceTypeUtils.FromName(Source),
            Timestamp = Timestamp
        };
    }
}