using System.Text.Json.Serialization;

namespace dotnet_service.Dtos;

public class MessageDto
{
    [JsonPropertyName("messageId")]
    public long MessageId { get; set; }
    
    [JsonPropertyName("conversation")]
    public string ConversationName { get; set; }
    
    [JsonPropertyName("timestamp")]
    public string SentAt { get; set; }
}