using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace dotnet_service.Entities;

public class Message
{
    [Key, Column(Order = 0)]
    public string ConversationName { get; set; }
    
    [Key, Column(Order = 1)]
    public long MessageId { get; set; }
    
    public required Conversation Conversation { get; set; }
    
    public string TimestampSent { get; set; }
    
    public string TimestampReceived { get; set; }
}