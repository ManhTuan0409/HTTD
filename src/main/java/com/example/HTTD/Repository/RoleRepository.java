package com.example.HTTD.Repository;

import com.example.HTTD.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepos extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
