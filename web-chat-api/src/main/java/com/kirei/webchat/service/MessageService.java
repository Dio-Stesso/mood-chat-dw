package com.kirei.webchat.service;

import com.kirei.webchat.model.ChatMessage;
import java.util.List;

public interface MessageService {
    List<ChatMessage> findAllByRoom(Long roomId);

    ChatMessage save(String messageText, String senderName, Long roomId);

    void deleteById(Long messageId);
}
