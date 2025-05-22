package com.kirei.webchat.service.mapper;

import com.kirei.webchat.model.ChatMessage;
import com.kirei.webchat.model.dto.MessageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageResponseDto mapToDto(ChatMessage message) {
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setId(message.getId());
        responseDto.setMessage(message.getMessage());
        responseDto.setSendDate(message.getSendDate());
        responseDto.setSenderName(message.getSender().getUsername());
        responseDto.setMood(message.getMood());
        return responseDto;
    }
}
