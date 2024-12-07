package internal.api.springbootservice.controller;

import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.payload.ConverstationRequest;
import internal.api.springbootservice.payload.MessageRequest;
import internal.api.springbootservice.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/conversations")
@AllArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping(value = "/start")
    public ResponseEntity<String> startConversation(@RequestBody ConverstationRequest request){
        conversationService.startConversation(request.getName());
        return new ResponseEntity<>("Conversation: " + request.getName() + " started", HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody MessageRequest request){
        conversationService.receiveMessage(request);
        return ResponseEntity.ok("Message received, sending next shortly");
    }
}