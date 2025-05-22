package com.kirei.webchat.service.mapper;

import com.kirei.webchat.model.Role;
import com.kirei.webchat.model.dto.RoleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto mapToDto(Role role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getRoleName());
        return responseDto;
    }
}
