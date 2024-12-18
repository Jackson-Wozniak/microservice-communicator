namespace dotnet_service.Dtos;

public class MessageDto
{
    public long MessageId { get; set; }
    public string ConversationName { get; set; }
    public string SentAt { get; set; }
}