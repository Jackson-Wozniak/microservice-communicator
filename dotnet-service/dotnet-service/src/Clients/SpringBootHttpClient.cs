using dotnet_service.Entities;

namespace dotnet_service.Clients;

public class SpringBootHttpClient
{
    public void SendNextMessage(Message message)
    {
        var newMessage = new SpringBootMessageRequest();
        
        //send to spring boot
    }
}