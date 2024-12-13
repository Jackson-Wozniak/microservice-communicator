package internal.api.springbootservice.client;

import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.properties.GlobalProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class DotnetHttpClient {

    private final RestTemplate restTemplate;

    public void sendNextMessage(Message oldMessage){
        DotnetMessageRequest message = new DotnetMessageRequest(oldMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap the object and headers into an HttpEntity
        HttpEntity<DotnetMessageRequest> request = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                GlobalProperties.DOTNET_HTTP_CLIENT_PATH, request, String.class);

        //handle responses here
    }

    public HttpStatus restartConversation(String conversation, LocalDateTime timestamp){
        return null;
    }
}
