package internal.api.springbootservice.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import internal.api.springbootservice.entity.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DotnetMessageRequest {

    @JsonProperty("conversation")
    private String conversation;
    @JsonProperty("messageId")
    private long messageId;
    @JsonProperty("timestamp")
    private String timestamp;

    public DotnetMessageRequest(Message message){
        this.conversation = message.getConversation().getConversationName();
        this.messageId = message.getMessageId().getMessageNumber() + 1;
        this.timestamp = LocalDateTime.now().toString();
    }
}
