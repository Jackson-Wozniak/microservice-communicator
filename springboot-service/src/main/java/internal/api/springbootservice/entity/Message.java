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
    @MapsId(value = "conversationName")
    @JoinColumn(name = "conversation_name")
    private Conversation conversation;

    @Column(name = "timestamp_received")
    private String timestampReceived;

    @Column(name = "timestamp_sent")
    private String timestampSent;

    public Message(Conversation conversation, long lastMessage,
                   String sentAt, String receivedAt) {
        this.messageId = new MessageId(conversation, lastMessage);
        this.conversation = conversation;
        this.timestampSent = sentAt;
        this.timestampReceived = receivedAt;
    }
}
