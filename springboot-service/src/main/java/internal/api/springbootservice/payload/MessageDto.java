package internal.api.springbootservice.payload;

import internal.api.springbootservice.entity.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private long messageNumber;
    private String timestampReceived;
    private String timestampSent;

    public MessageDto(Message message){
        this.messageNumber = message.getMessageId().getMessageNumber();
        this.timestampReceived = message.getTimestampReceived();
        this.timestampSent = message.getTimestampSent();
    }
}
