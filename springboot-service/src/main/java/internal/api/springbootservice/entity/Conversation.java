package internal.api.springbootservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "conversation")
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
public class Conversation {

    @Id
    @Column(name = "conversation_name")
    private String name;

    @Column(name = "began_at")
    private LocalDateTime beganAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    public Conversation(String name, LocalDateTime beganAt){
        this.name = name;
        this.beganAt = beganAt;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public static boolean isValidName(String name){
        //go through checks to ensure valid name
        return true;
    }
}
