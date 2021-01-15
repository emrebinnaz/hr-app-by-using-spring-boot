package com.example.hrapp.hrapp.Repository;

import com.example.hrapp.hrapp.Domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    Privilege findByName(String name);
}