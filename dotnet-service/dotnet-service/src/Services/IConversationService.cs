using dotnet_service.Dtos;
using dotnet_service.Entities;

namespace dotnet_service.Services;

public interface IConversationService
{
    List<Conversation> FindAllConversations();
    Task<Conversation> FindConversationByName(string name);
    void ClearAndBeginConversation(string name);
    void BeginConversation(string name);
    void ReceiveMessage(MessageDto request);
}