using dotnet_service.Entities;
using Microsoft.EntityFrameworkCore;

namespace dotnet_service.Data;

public class MessageDbContext : DbContext
{
    public DbSet<Message> Messages;
    
    public MessageDbContext(DbContextOptions<MessageDbContext> options): base(options) { }
}