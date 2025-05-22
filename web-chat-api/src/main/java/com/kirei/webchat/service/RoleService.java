package com.kirei.webchat.service;

import com.kirei.webchat.model.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role getRoleByName(Role.RoleName roleName);

    List<Role> findAll();
}
