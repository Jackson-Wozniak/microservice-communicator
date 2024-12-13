package internal.api.springbootservice.client;

import internal.api.springbootservice.entity.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DotnetMessageRequest {

    private String conversation;
    private long messageId;
    private String timestamp;

    public DotnetMessageRequest(Message message){
        this.conversation = message.getConversation().getConversationName();
        this.messageId = message.getMessageId().getMessageNumber() + 1;
        this.timestamp = LocalDateTime.now().toString();
    }
}
