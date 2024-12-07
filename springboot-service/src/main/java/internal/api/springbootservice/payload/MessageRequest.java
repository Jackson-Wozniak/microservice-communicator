package internal.api.springbootservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Getter
@Setter
public class MessageRequest {

    private String conversation;
    private long messageId;
    private String timestamp;
}
