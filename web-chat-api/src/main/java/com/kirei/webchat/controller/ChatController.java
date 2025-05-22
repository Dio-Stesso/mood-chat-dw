package com.kirei.webchat.controller;

import com.kirei.webchat.model.dto.MessageResponseDto;
import com.kirei.webchat.model.dto.RoomResponseDto;
import com.kirei.webchat.service.MessageService;
import com.kirei.webchat.service.RoomService;
import com.kirei.webchat.service.mapper.MessageMapper;
import com.kirei.webchat.service.mapper.RoomMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
@CrossOrigin("*")
public class ChatController {
    private final RoomService roomService;
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private final RoomMapper roomMapper;

    public ChatController(RoomService roomService,
                          MessageService messageService,
                          MessageMapper messageMapper,
                          RoomMapper roomMapper) {
        this.roomService = roomService;
        this.messageService = messageService;
        this.messageMapper = messageMapper;
        this.roomMapper = roomMapper;
    }

    @GetMapping
    public List<RoomResponseDto> chats(Authentication authentication) {
        return roomService.findByUsername(authentication.getName())
                .stream()
                .map(room -> roomMapper.toDto(room.getFirstUserUsername()
                                .equals(authentication.getName())
                                ? room.getSecondUserUsername() : room.getFirstUserUsername(),
                        "", room.getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{roomId}")
    public List<MessageResponseDto> messagesByRoomId(@PathVariable Long roomId) {
        return messageService.findAllByRoom(roomId)
                .stream()
                .map(messageMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{roomId}")
    public void deleteChat(@PathVariable Long roomId) {
        roomService.delete(roomService.findById(roomId));
    }
}
