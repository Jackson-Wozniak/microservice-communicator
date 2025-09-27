using dotnet_service.DTOs;
using dotnet_service.Entities;

namespace dotnet_service.Services;

public interface IMessageService
{
    IEnumerable<Message> GetMessages();
    void StartConversation(string conversationName);
    Task<bool> ReceiveAndQueueNextMessage(MessageDTO message);
}