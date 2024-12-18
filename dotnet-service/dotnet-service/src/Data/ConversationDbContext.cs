using dotnet_service.Entities;
using Microsoft.EntityFrameworkCore;

namespace dotnet_service.Data;

public class ConversationDbContext : DbContext
{
    public ConversationDbContext(DbContextOptions<ConversationDbContext> options): base(options) { }
    
    public DbSet<Conversation> Conversations { get; set; }
    public DbSet<Message> Messages { get; set; }
}