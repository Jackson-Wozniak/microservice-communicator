using dotnet_service.Entities;

namespace dotnet_service.Clients;

public class SpringBootHttpClient
{
    private static readonly string SpringBootSendMessage = "http://localhost:8080/api/v1/conversations";
    
    public void SendNextMessage(Message message)
    {
        var newMessage = SpringBootMessageRequest.nextMessage(message);
        
        //send to spring boot
    }
}