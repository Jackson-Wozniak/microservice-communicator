using System.ComponentModel.DataAnnotations;

namespace dotnet_service.Entities;

public class Conversation
{
    [Key]
    public string Name { get; set; }

    public List<Message> Messages { get; set; } = new List<Message>();

    public DateTime BeganAt { get; set; }

    public Conversation(string name, DateTime beganAt)
    {
        Name = name;
        BeganAt = beganAt;
    }
}