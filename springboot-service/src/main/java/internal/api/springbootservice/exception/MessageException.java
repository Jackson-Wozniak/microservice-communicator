package internal.api.springbootservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageException extends RuntimeException{

    private final HttpStatus status;

    private MessageException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public static MessageException invalidTimestamp(){
        return new MessageException("Invalid timestamp given in JSON request", HttpStatus.BAD_REQUEST);
    }
}
