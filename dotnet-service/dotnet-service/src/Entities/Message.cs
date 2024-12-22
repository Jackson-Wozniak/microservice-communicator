using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace dotnet_service.Entities;

public class Message
{
    public string ConversationName { get; set; }
    
    public long MessageId { get; set; }
    
    public Conversation Conversation { get; set; }
    
    public string TimestampSent { get; set; }
    
    public string TimestampReceived { get; set; }

    public Message()
    {
        
    }
    
    public Message(long messageId, Conversation conversation, string sentAt, string receivedAt)
    {
        MessageId = messageId;
        Conversation = conversation;
        ConversationName = conversation.Name;
        TimestampSent = sentAt;
        TimestampReceived = receivedAt;
    }
}