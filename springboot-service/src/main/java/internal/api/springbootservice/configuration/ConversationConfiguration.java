package internal.api.springbootservice.configuration;

import internal.api.springbootservice.client.DotnetHttpClient;
import internal.api.springbootservice.entity.Conversation;
import internal.api.springbootservice.entity.Message;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/*
Begins the conversation. This MUST run to start the conversation and send the initial message.
Currently, there is no fallback on failure, but this could be added later on
 */
@Configuration
@AllArgsConstructor
public class ConversationConfiguration {

    private final DotnetHttpClient dotnetHttpClient;

    @PostConstruct
    public void beginConversation() {
        /*
        TODO:
            - clear conversation if one already exists
            - send request to clear dotnet conversation
            - begin new conversation
         */
        dotnetHttpClient.sendNextMessage(new Message(new Conversation("Test", LocalDateTime.now()),
                0, LocalDateTime.now().toString(), LocalDateTime.now().toString()));
    }
}
