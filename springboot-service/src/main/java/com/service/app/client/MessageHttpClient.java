package com.service.app.client;

import com.service.app.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class MessageHttpClient {
    private final RestTemplate restTemplate;

    public void sendMessage(Message message){

    }
}
