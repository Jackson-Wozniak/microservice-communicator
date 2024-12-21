using dotnet_service.Entities;

namespace dotnet_service.Clients;

public class SpringBootMessageRequest
{
    public long MessageId { get; set; }
    
    public string ConversationName { get; set; }
    
    public string TimestampSent { get; set; }

    private SpringBootMessageRequest()
    {
        
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