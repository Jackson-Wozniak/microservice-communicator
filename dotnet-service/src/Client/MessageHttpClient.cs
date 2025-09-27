using System.Text;
using System.Text.Json;
using dotnet_service.DTOs;
using dotnet_service.Entities;

namespace dotnet_service.Client;

public static class MessageHttpClient
{
    private static readonly HttpClient Client = new HttpClient();
    private static readonly string Url = "http://localhost:8080/api/v1/message";
    
    public static void Send(Message message)
    {
        using StringContent jsonContent = new(
            JsonSerializer.Serialize(new MessageDTO(message)),
            Encoding.UTF8, "application/json");
        using HttpResponseMessage m = Client.PostAsync(Url, jsonContent).Result;
        Console.WriteLine(m.Content.ReadAsStringAsync().Result);
    }
}