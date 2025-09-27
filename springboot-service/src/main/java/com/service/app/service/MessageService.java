package com.service.app.service;

import com.service.app.client.MessageHttpClient;
import com.service.app.dto.MessageDto;
import com.service.app.entity.Message;
import com.service.app.enums.SourceType;
import com.service.app.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageHttpClient messageHttpClient;

    public List<Message> findMessages(){
        return messageRepository.findAll();
    }

    public void startConversation(String conversationName){
        Message message = new Message(conversationName, SourceType.SPRINGBOOT_SERVICE,
                1, Instant.now());
        messageHttpClient.sendMessage(message);
    }

    public void receiveAndSendNextMessage(MessageDto messageDto){
        messageRepository.save(messageDto.toMessage());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message next = new Message(messageDto.getConversation(),
                        SourceType.fromName(messageDto.getSourceType()),
                        messageDto.getMessageNumber() + 1,
                        messageDto.getTimestamp().toInstant());
                messageHttpClient.sendMessage(next);
            }
        }, 10000);
    }
}
