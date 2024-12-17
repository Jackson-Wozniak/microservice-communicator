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
                GlobalProperties.DOTNET_CLIENT_NEW_MESSAGE, request, String.class);

        //handle responses here
        /*
        The idea here would be to have a fallback mechanism where if the message request fails,
        the conversation is deleted using restartConversation() on Dotnets end and then here
        with spring boot we restart the conversation scheduling to start with message 1

        We also need to delete the conversation from this database as well, however hypothetically
        we do not need to delete ALL conversations just the one with the failed request
         */
    }

    public HttpStatus restartConversation(String conversation, LocalDateTime timestamp){
        return null;
    }
}
