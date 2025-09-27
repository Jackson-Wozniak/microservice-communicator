using dotnet_service.Enums;

namespace dotnet_service.Entities;

public class Message
{
    public long Id { get; set; }
    public SourceType Source { get; set; }
    public long MessageNumber { get; set; }
    public DateTime Timestamp { get; set; }
}