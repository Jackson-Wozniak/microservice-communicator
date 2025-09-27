using System.Text.Json.Serialization;
using dotnet_service.Entities;
using dotnet_service.Enums;

namespace dotnet_service.DTOs;

public class MessageDTO
{
    public string SourceType { get; set; }
    public string Conversation { get; set; }
    public long MessageNumber { get; set; }
    public DateTime Timestamp { get; set; }
    
    [JsonConstructor]
    public MessageDTO(string sourceType, string conversation, 
        long messageNumber, DateTime timestamp)
    {
        SourceType = sourceType;
        Conversation = conversation;
        MessageNumber = messageNumber;
        Timestamp = timestamp;
    }
    
    public MessageDTO(Message message)
    {
        SourceType = message.Source.ToName();
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
            Source = SourceTypeUtils.FromName(SourceType),
            Timestamp = Timestamp
        };
    }
}