package internal.api.springbootservice.controller;

import internal.api.springbootservice.entity.Conversation;
import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.exception.ConversationException;
import internal.api.springbootservice.payload.ConversationDto;
import internal.api.springbootservice.payload.ConversationRequest;
import internal.api.springbootservice.payload.MessageRequest;
import internal.api.springbootservice.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/conversations")
@AllArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<ConversationDto>> getConversations(){
        return ResponseEntity.ok(conversationService.findAllConversations().stream()
                .map(ConversationDto::new).toList());
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<ConversationDto> getConversation(@PathVariable("name") String name){
        Conversation conversation = conversationService.findConversationByName(name);
        return ResponseEntity.ok(new ConversationDto(conversation));
    }

    @PostMapping(value = "/start")
    public ResponseEntity<String> startConversation(@RequestBody MessageRequest request){
        conversationService.startConversation(request);
        return new ResponseEntity<>("Conversation: " + request.getConversation() + " started", HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody MessageRequest request){
        try{
            conversationService.receiveMessage(request);
        }catch (ConversationException ex){
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
        return ResponseEntity.ok("Message received, sending next shortly");
    }
}
