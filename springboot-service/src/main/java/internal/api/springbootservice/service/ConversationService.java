package internal.api.springbootservice.service;

import internal.api.springbootservice.client.DotnetHttpClient;
import internal.api.springbootservice.entity.Conversation;
import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.exception.ConversationException;
import internal.api.springbootservice.exception.MessageException;
import internal.api.springbootservice.payload.MessageRequest;
import internal.api.springbootservice.repository.ConversationRepository;
import internal.api.springbootservice.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final DotnetHttpClient dotnetHttpClient;

    public List<Conversation> findAllConversations(){
        return conversationRepository.findAll();
    }

    public Conversation findConversationByName(String name){
        return conversationRepository.findById(name)
                .orElseThrow(() -> ConversationException.notFound("cannot find: " + name));
    }

    @Transactional
    @Modifying
    public void deleteAll(){
        messageRepository.deleteAll();
        conversationRepository.deleteAll();
    }

    @Transactional
    @Modifying
    public void startConversation(MessageRequest req){
        LocalDateTime receivedAt = LocalDateTime.now();
        if(!Conversation.isValidName(req.getConversation())){
            throw ConversationException.createException(
                    "Conversation: " + req.getConversation() + " is not valid name");
        }
        Optional<Conversation> conversation = conversationRepository.findById(req.getConversation());
        conversation.ifPresent(convo -> {
            conversationRepository.delete(convo);
        });

        Conversation createdConvo = conversationRepository.save(new Conversation(req.getConversation(), receivedAt));

        Message message = new Message(createdConvo, req.getMessageId(), req.getTimestamp(), receivedAt.toString());
        createdConvo.addMessage(message);
        conversationRepository.save(createdConvo);

        //Now schedule new message to be sent to Http Client
        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS)
                .execute(() -> dotnetHttpClient.sendNextMessage(message));
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
