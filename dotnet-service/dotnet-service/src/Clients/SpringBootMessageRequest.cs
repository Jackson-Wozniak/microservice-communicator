using System.Text.Json.Serialization;
using dotnet_service.Entities;

namespace dotnet_service.Clients;

public class SpringBootMessageRequest
{
    [JsonPropertyName("messageId")]
    public long MessageId { get; set; }
    
    [JsonPropertyName("conversation")]
    public string ConversationName { get; set; }
    
    [JsonPropertyName("timestamp")]
    public string TimestampSent { get; set; }

    private SpringBootMessageRequest()
    {
        
    }

    public static SpringBootMessageRequest firstMessage(string conversation)
    {
        var newMessage = new SpringBootMessageRequest
        {
            MessageId = 1,
            ConversationName = conversation,
            TimestampSent = DateTime.Now.ToString("hh:mm:ss")
        };
        return newMessage;
    }
    
    public static SpringBootMessageRequest nextMessage(Message message)
    {
        var newMessage = new SpringBootMessageRequest();
        newMessage.MessageId = message.MessageId + 1;
        newMessage.ConversationName = message.ConversationName;
        newMessage.TimestampSent = DateTime.Now.ToString("hh:mm:ss");
        return newMessage;
    }
}