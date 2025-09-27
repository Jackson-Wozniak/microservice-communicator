using dotnet_service.DTOs;
using dotnet_service.Services;
using Microsoft.AspNetCore.Mvc;

namespace dotnet_service.Controllers;

[ApiController]
[Route("api/v1/Message")]
public class MessageController(IMessageService messageService) : ControllerBase
{
    [HttpGet]
    public IEnumerable<MessageDTO> GetMessages()
    {
        return messageService.GetMessages()
            .Select(m => new MessageDTO(m));
    }

    [HttpPost("start")]
    public IActionResult StartConversation([FromQuery] string conversation)
    {
        messageService.StartConversation(conversation);
        return Ok();
    }

    [HttpPost]
    public async Task<IActionResult> ReceiveMessage([FromBody] MessageDTO message)
    {
        await messageService.ReceiveAndQueueNextMessage(message);
        return Ok();
    }
}