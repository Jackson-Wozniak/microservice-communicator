using dotnet_service.Entities;

namespace dotnet_service.Services;

public interface IMessageService
{
    IEnumerable<Message> GetMessages();
}