package com.service.app.dto;

import com.service.app.entity.Message;
import com.service.app.enums.SourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    private String conversation;
    private String sourceType;
    private long messageNumber;
    private OffsetDateTime timestamp;

    public MessageDto(Message message){
        this.conversation = message.getConversation();
        this.sourceType = message.getSourceType().getName();
        this.messageNumber = message.getMessageNumber();
        this.timestamp = message.getTimestamp().atOffset(ZoneOffset.UTC);
    }

    public Message toMessage(){
        return new Message(conversation, SourceType.fromName(sourceType), messageNumber, timestamp.toInstant());
    }
}
