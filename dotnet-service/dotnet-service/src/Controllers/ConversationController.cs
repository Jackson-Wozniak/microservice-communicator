using dotnet_service.Dtos;
using dotnet_service.Services;
using Microsoft.AspNetCore.Mvc;

namespace dotnet_service.Controllers;

[Route("api/Conversations")]
[ApiController]
public class ConversationController : ControllerBase
{
    private readonly IConversationService _conversationService;

    public ConversationController(IConversationService conversationService)
    {
        _conversationService = conversationService;
    }

    [HttpGet]
    public ActionResult<List<MessageDto>> GetConversations()
    {
        return Ok(_conversationService.FindAllConversations());
    }

    [HttpGet("/{name}")]
    public ActionResult<List<MessageDto>> GetConversation(string name)
    {
        var convo = _conversationService.FindConversationByName(name);
        return Ok(convo);
    }
    
    [HttpPost]
    [Route("/start")]
    public ActionResult<string> StartConversation([FromQuery] string name)
    {
        _conversationService.BeginConversation(name);

        return Created();
    }

    [HttpPost]
    public ActionResult<string> ReceiveMessage([FromBody] MessageDto request)
    {
        _conversationService.ReceiveMessage(request);

        return Ok();
    }
}