package internal.api.springbootservice.configuration;

import internal.api.springbootservice.client.DotnetHttpClient;
import internal.api.springbootservice.entity.Conversation;
import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.properties.GlobalProperties;
import internal.api.springbootservice.service.ConversationService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS)
                .execute(() -> dotnetHttpClient.startConversation(GlobalProperties.SPRING_BOOT_MESSAGE_NAME));
    }
}
