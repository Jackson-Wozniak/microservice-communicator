package com.service.app.entity;

import com.service.app.enums.SourceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity(name = "message")
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversation_name")
    private String conversation;

    @Column(name = "source_type")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "message_number")
    private Long messageNumber;

    @Column(name = "timestamp")
    private Instant timestamp;

    public Message(String conversation, SourceType sourceType,
                   long messageNumber, Instant timestamp) {
        this.conversation = conversation;
        this.sourceType = sourceType;
        this.messageNumber = messageNumber;
        this.timestamp = timestamp;
    }
}
