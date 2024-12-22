package internal.api.springbootservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MessageId implements Serializable {

    @Column(name = "conversation_name")
    private String conversationName;

    @Column(name = "message_number")
    private Long messageNumber;

    public MessageId(Conversation conversation, long previousMessage){
        conversationName = conversation.getConversationName();
        messageNumber = previousMessage;
    }
}
