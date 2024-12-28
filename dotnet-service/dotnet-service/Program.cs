using dotnet_service.Clients;
using dotnet_service.Configurations;
using dotnet_service.Data;
using dotnet_service.Services;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

var connectionString = builder.Configuration.GetConnectionString("ConversationDbConnection");
builder.Services.AddDbContext<ConversationDbContext>(options =>
{
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
});

builder.Services.AddSingleton<SpringBootHttpClient>();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddHostedService<ConversationConfiguration>();
builder.Services.AddScoped<IConversationService, ConversationService>();

builder.Services.AddControllers();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors(x => x
    .AllowAnyMethod()
    .AllowAnyHeader()
    .SetIsOriginAllowed(_ => true)
    .AllowCredentials());

app.UseAuthorization();

app.MapControllers();

app.Run();