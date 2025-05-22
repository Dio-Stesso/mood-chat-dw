package com.kirei.webchat.model.dto;

import java.awt.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageResponseDto {
    private Long id;
    private String senderName;
    private LocalDateTime sendDate;
    private String message;
    private int mood;
}
