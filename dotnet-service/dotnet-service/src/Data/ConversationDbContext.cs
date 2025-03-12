using dotnet_service.Entities;
using Microsoft.EntityFrameworkCore;

namespace dotnet_service.Data;

public class ConversationDbContext : DbContext
{
    public ConversationDbContext(DbContextOptions<ConversationDbContext> options): base(options) { }
    
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        modelBuilder.Entity<Message>()
            .HasKey(m => new { m.ConversationName, m.MessageId });
        
        modelBuilder.Entity<Conversation>()
            .HasMany(c => c.Messages)
            .WithOne(m => m.Conversation)
            .HasForeignKey(m => m.ConversationName)
            .OnDelete(DeleteBehavior.Cascade);
    }
    
    public DbSet<Conversation> Conversations { get; set; }
    public DbSet<Message> Messages { get; set; }
}