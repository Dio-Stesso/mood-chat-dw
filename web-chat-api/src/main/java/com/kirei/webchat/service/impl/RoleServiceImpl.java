package com.kirei.webchat.service.impl;

import com.kirei.webchat.model.Role;
import com.kirei.webchat.repository.RoleRepository;
import com.kirei.webchat.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getRoleByName(Role.RoleName roleName) {
        return repository.findByName(roleName.name())
                .orElseThrow(() -> new RuntimeException("Can`t find Role by name " + roleName));
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}
