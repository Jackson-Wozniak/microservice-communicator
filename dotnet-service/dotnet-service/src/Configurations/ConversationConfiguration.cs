using dotnet_service.Clients;
using dotnet_service.Services;

namespace dotnet_service.Configurations;

public class ConversationConfiguration : IHostedService
{
    private static readonly string ConversationName = "Dotnet-Conversation";
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
        conversationService.DeleteAll();
        
        Thread.Sleep(10000); //pause so that spring boot boots up as well
        var springBootHttpClient = scope.ServiceProvider.GetRequiredService<SpringBootHttpClient>();
        
        springBootHttpClient.SendFirstMessage().Wait();
    }
}