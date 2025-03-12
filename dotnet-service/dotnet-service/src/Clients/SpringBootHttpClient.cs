using System.Text;
using System.Text.Json;
using dotnet_service.Entities;

namespace dotnet_service.Clients;

public class SpringBootHttpClient
{
    private static readonly string DotnetConversationName = "Dotnet-Conversation";
    private static readonly string SpringBootStartConversation = "http://localhost:8080/api/v1/conversations/start";
    private static readonly string SpringBootSendMessage = "http://localhost:8080/api/v1/conversations";
    
    /*
     * This is a 2 part method where we first send a request to begin the conversation on the
     * opposite server and then send the first message afterward
     */
    public async Task SendFirstMessage()
    {
        using var client = new HttpClient();
        var firstMessage = SpringBootMessageRequest.firstMessage(DotnetConversationName);
        try
        {
            var messageContent = JsonSerializer.Serialize(firstMessage);
            var content = new StringContent(messageContent, Encoding.UTF8, "application/json");

            var response = await client.PostAsync(SpringBootStartConversation, content);
            response.EnsureSuccessStatusCode();
            Console.WriteLine("Resource created successfully.");
        }
        catch (HttpRequestException e)
        {
            Console.WriteLine($"Request error: {e.Message}");
        }
    }
    
    public async void SendNextMessage(Message message)
    {
        var newMessage = SpringBootMessageRequest.nextMessage(message);
        
        using var client = new HttpClient();
        //send to spring boot
        try
        {
            var messageContent = JsonSerializer.Serialize(newMessage);
            var content = new StringContent(messageContent, Encoding.UTF8, "application/json");

            var response = await client.PostAsync(SpringBootSendMessage, content);
            response.EnsureSuccessStatusCode();
            Console.WriteLine("Resource created successfully.");
        }
        catch (HttpRequestException e)
        {
            Console.WriteLine($"Request error: {e.Message}");
        }
    }
}