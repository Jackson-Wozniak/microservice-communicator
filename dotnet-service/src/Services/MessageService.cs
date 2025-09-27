using dotnet_service.Data;
using dotnet_service.Entities;

namespace dotnet_service.Services;

public class MessageService : IMessageService
{
    private readonly MessageDbContext _messageDbContext;

    public MessageService(MessageDbContext m)
    {
        _messageDbContext = m;
    }

    public IEnumerable<Message> GetMessages()
    {
        return _messageDbContext.Messages;
    }
}