package com.kirei.webchat.service.mapper;

import com.kirei.webchat.model.dto.RoomResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public RoomResponseDto toDto(String username, String lastMessage, Long id) {
        RoomResponseDto responseDto = new RoomResponseDto();
        responseDto.setId(id);
        responseDto.setName(username);
        responseDto.setLastMessage(lastMessage);
        return responseDto;
    }
}
