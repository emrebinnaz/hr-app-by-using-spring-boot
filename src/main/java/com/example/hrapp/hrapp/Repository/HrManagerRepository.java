package com.example.hrapp.hrapp.Repository;

import com.example.hrapp.hrapp.Domain.HrManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HrManagerRepository extends JpaRepository<HrManager, UUID>  {

    HrManager findByUsername(String username);
}
