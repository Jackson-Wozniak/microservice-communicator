package internal.api.springbootservice.client;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public interface DotnetHttpClient {
    HttpStatus sendMessage(int messageId, String conversation, LocalDateTime timestamp);
    HttpStatus restartConversation(String conversation, LocalDateTime timestamp);
}
