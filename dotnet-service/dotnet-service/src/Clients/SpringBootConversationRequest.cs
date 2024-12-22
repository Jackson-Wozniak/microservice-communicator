using System.Text.Json.Serialization;

namespace dotnet_service.Clients;

public class SpringBootConversationRequest
{
    [JsonPropertyName("name")]
    public string Name { get; set; }
}