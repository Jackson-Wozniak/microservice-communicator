using dotnet_service.Entities;

namespace dotnet_service.Services;

public interface IConversationService
{
    Task<Conversation> FindConversationByName(string name);
    void ClearAndBeginConversation(string name);
}