package com.service.app.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.app.dto.MessageDto;
import com.service.app.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.time.ZoneOffset;
import java.util.Collections;

@Component
@AllArgsConstructor
public class MessageHttpClient {
    private final RestTemplate restTemplate;

    public void sendMessage(Message message){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MessageDto messageDto = new MessageDto(message);
        messageDto.setTimestamp(messageDto.getTimestamp().withOffsetSameInstant(ZoneOffset.UTC));

        try{
            String json = mapper.writeValueAsString(messageDto);

            HttpEntity<String> body = new HttpEntity<>(json, headers);
            restTemplate.exchange("http://localhost:5029/api/v1/Message",
                    HttpMethod.POST, body, String.class);
        }catch(Exception ignored){ }
    }
}
