package com.kirei.webchat.service.impl;

import com.kirei.webchat.model.ChatRoom;
import com.kirei.webchat.repository.ChatRoomRepository;
import com.kirei.webchat.service.RoomService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final ChatRoomRepository repository;

    public RoomServiceImpl(ChatRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatRoom findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Room by id not founded " + id));
    }

    @Override
    public ChatRoom save(ChatRoom room) {
        return repository.save(room);
    }

    @Override
    public void delete(ChatRoom room) {
        repository.delete(room);
    }

    @Override
    public List<ChatRoom> findByUsername(String username) {
        return repository.findByUser(username);
    }

    @Override
    public ChatRoom createRoom(String firstUser, String secondUser) {
        Optional<ChatRoom> optionalChatRoom =
                repository.findByFirstUserUsernameAndSecondUserUsernameIn(firstUser, secondUser);
        if (optionalChatRoom.isPresent()) {
            return optionalChatRoom.get();
        }
        ChatRoom room = new ChatRoom();
        room.setFirstUserUsername(firstUser);
        room.setSecondUserUsername(secondUser);
        return repository.save(room);
    }
}
