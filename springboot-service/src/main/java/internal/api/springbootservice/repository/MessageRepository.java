package internal.api.springbootservice.repository;

import internal.api.springbootservice.entity.Message;
import internal.api.springbootservice.entity.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, MessageId> {
}
