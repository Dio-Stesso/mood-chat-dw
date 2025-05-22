package com.kirei.webchat.service.impl;

import com.kirei.webchat.model.ChatMessage;
import com.kirei.webchat.model.ChatRoom;
import com.kirei.webchat.repository.ChatMessageRepository;
import com.kirei.webchat.repository.ChatRoomRepository;
import com.kirei.webchat.service.MessageService;
import com.kirei.webchat.service.UserService;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final ChatMessageRepository messageRepository;
    private final ChatRoomRepository roomRepository;
    private final UserService userService;
    private final MoodAnalysisService moodService;

    public MessageServiceImpl(ChatMessageRepository messageRepository,
                              ChatRoomRepository roomRepository,
                              UserService userService, MoodAnalysisService moodService) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userService = userService;
        this.moodService = moodService;
    }

    @Override
    public List<ChatMessage> findAllByRoom(Long roomId) {
        List<ChatMessage> chatMessages = messageRepository.findAllByRoom(
                roomRepository.findById(roomId).orElseThrow(() ->
                        new RuntimeException("Room not founded, id " + roomId)));
        return chatMessages.stream()
                .sorted(Comparator.comparing(ChatMessage::getSendDate))
                .collect(Collectors.toList());
    }

    @Override
    public ChatMessage save(String messageText, String senderName, Long roomId) {
        Optional<ChatRoom> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()
                && (roomOptional.get().getFirstUserUsername().equals(senderName)
                || roomOptional.get().getSecondUserUsername().equals(senderName))) {
            ChatMessage message = new ChatMessage();
            message.setMessage(messageText);
            message.setRoom(roomOptional.get());
            message.setSender(userService
                    .findByUsernameOrEmail(senderName, "")
                    .orElseThrow(() -> new RuntimeException(
                            "Can't find the user by name: " + senderName)));
            message.setSendDate(LocalDateTime.now());
            message.setMood(moodService.analyzeMood(messageText));

            return messageRepository.save(message);
        }
        throw new RuntimeException("Can`t send message by properties: "
                + senderName + " " + roomId);
    }

    @Override
    public void deleteById(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
