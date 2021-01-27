package com.example.hrapp.hrapp.Repository;

import com.example.hrapp.hrapp.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    List<Role> findAllByName(String name);

    boolean existsByName(String name);

}
