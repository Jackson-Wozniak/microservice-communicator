package com.service.app.controller;

import com.service.app.dto.MessageDto;
import com.service.app.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/message")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(){
        return ResponseEntity.ok(messageService.findMessages()
                .stream().map(MessageDto::new).toList());
    }

    @PostMapping(value = "/start")
    public void startConversation(@RequestParam String conversation){
        messageService.startConversation(conversation);
    }

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody MessageDto message){
        messageService.receiveAndSendNextMessage(message);
        return ResponseEntity.ok("Sent");
    }
}
