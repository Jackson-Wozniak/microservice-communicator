package internal.api.springbootservice.service;

import internal.api.springbootservice.client.DotnetHttpClient;
import internal.api.springbootservice.entity.Conversation;
import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.exception.ConversationException;
import internal.api.springbootservice.exception.MessageException;
import internal.api.springbootservice.payload.MessageRequest;
import internal.api.springbootservice.repository.ConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final DotnetHttpClient dotnetHttpClient;

    public Conversation findConversationByName(String name){
        return conversationRepository.findById(name)
                .orElseThrow(() -> ConversationException.notFound("cannot find: " + name));
    }

    public void startConversation(String name){
        if(!Conversation.isValidName(name)){
            throw ConversationException.createException("Conversation: " + name + " is not valid name");
        }
        if(conversationRepository.findById(name).isPresent()){
            throw ConversationException.createException("Conversation: " + name + " already exists");
        }
        conversationRepository.save(new Conversation(name, LocalDateTime.now()));
    }

    public void receiveMessage(MessageRequest req){
        LocalDateTime receivedAt = LocalDateTime.now();
        Optional<Conversation> conversation = conversationRepository.findById(req.getConversation());
        if(conversation.isEmpty()){
            conversation = Optional.of(new Conversation(req.getConversation(), LocalDateTime.now()));
            conversationRepository.save(conversation.get());
        }

        Message message = new Message(conversation.get(), req.getMessageId(), req.getTimestamp(), receivedAt.toString());
        conversation.get().addMessage(message);
        conversationRepository.save(conversation.get());

        //Now schedule new message to be sent to Http Client
        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS)
                .execute(() -> dotnetHttpClient.sendNextMessage(message));
    }
}
