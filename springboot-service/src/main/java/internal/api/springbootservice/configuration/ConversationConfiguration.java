package internal.api.springbootservice.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

/*
Begins the conversation. This MUST run to start the conversation and send the initial message.
Currently, there is no fallback on failure, but this could be added later on
 */
@Configuration
@AllArgsConstructor
public class ConversationConfiguration {

    @PostConstruct
    public void beginConversation(){
        /*
        TODO:
            - clear conversation if one already exists
            - send request to clear dotnet conversation
            - begin new conversation
         */
    }
}
