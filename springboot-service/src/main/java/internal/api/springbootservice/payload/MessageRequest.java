package internal.api.springbootservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageRequest {

    private String conversation;
    private long messageId;
    private LocalDateTime timestamp;
}
