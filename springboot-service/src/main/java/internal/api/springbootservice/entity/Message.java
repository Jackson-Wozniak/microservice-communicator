package internal.api.springbootservice.entity;

import internal.api.springbootservice.payload.MessageRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "message")
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @EmbeddedId
    private MessageId messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "name")
    @JoinColumn(name = "conversation_name")
    private Conversation conversation;

    @Column(name = "timestamp_received")
    private LocalDateTime timestampReceived;

    @Column(name = "timestamp_sent")
    private LocalDateTime timestampSent;

    public Message(Conversation conversation, long lastMessage,
                   LocalDateTime sentAt, LocalDateTime receivedAt) {
        this.messageId = new MessageId(conversation, lastMessage);
        this.conversation = conversation;
        this.timestampSent = sentAt;
        this.timestampReceived = receivedAt;
    }
}
