package internal.api.springbootservice.payload;

import internal.api.springbootservice.entity.Conversation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {

    private String conversationName;
    private String beganAt;
    private List<MessageDto> messages;

    public ConversationDto(Conversation conversation){
        this.conversationName = conversation.getConversationName();
        this.beganAt = conversation.getBeganAt().toString();
        this.messages = conversation.getMessages().stream()
                .map(MessageDto::new)
                .toList();
    }
}
