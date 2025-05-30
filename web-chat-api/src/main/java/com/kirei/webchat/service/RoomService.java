package com.kirei.webchat.service;

import com.kirei.webchat.model.ChatRoom;
import java.util.List;

public interface RoomService {
    ChatRoom findById(Long id);

    ChatRoom save(ChatRoom room);

    void delete(ChatRoom room);

    List<ChatRoom> findByUsername(String username);

    ChatRoom createRoom(String firstUser, String secondUser);
}
