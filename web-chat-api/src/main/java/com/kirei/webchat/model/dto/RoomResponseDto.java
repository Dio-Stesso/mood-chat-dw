package com.kirei.webchat.model.dto;

import lombok.Data;

@Data
public class RoomResponseDto {
    private Long id;
    private String name;
    private String lastMessage;

}
