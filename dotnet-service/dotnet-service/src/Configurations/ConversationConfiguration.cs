using dotnet_service.Services;

namespace dotnet_service.Configurations;

public class ConversationConfiguration : IHostedService
{
    private static readonly string ConversationName = "DOTNET CONVERSATION";
    private readonly IServiceProvider _serviceProvider;
    
    public ConversationConfiguration(IServiceProvider serviceProvider)
    {
        _serviceProvider = serviceProvider;
    }
    
    public Task StartAsync(CancellationToken cancellationToken)
    {
        InitializeConversation();
        return Task.CompletedTask;
    }

    public Task StopAsync(CancellationToken cancellationToken)
    {
        return Task.CompletedTask;
    }
    
    private void InitializeConversation()
    {
        using var scope = _serviceProvider.CreateScope();
        var conversationService = scope.ServiceProvider.GetRequiredService<IConversationService>();
            
        conversationService.ClearAndBeginConversation(ConversationName);
        //now send first message to client
    }
}