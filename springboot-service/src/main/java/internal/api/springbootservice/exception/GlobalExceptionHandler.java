package internal.api.springbootservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<String> handleMessageException(MessageException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(ConversationException.class)
    public ResponseEntity<String> handleConversationException(ConversationException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
