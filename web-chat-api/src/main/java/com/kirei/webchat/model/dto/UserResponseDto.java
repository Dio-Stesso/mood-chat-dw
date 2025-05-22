package com.kirei.webchat.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private List<RoleResponseDto> roles;

}
