package internal.api.springbootservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConversationException extends RuntimeException{

    private final HttpStatus status;

    private ConversationException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public static ConversationException createException(String message){
        return new ConversationException(message, HttpStatus.BAD_REQUEST);
    }

    public static ConversationException notFound(String message){
        return new ConversationException(message, HttpStatus.NOT_FOUND);
    }
}
