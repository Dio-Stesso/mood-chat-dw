package com.kirei.webchat.repository;

import com.kirei.webchat.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("FROM Role r WHERE r.roleName=?1")
    Optional<Role> findByName(String roleName);
}
