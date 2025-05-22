package com.kirei.webchat.repository;

import com.kirei.webchat.model.ChatMessage;
import com.kirei.webchat.model.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByRoom(ChatRoom chatRoom);

    void deleteById(Long messageId);
}
