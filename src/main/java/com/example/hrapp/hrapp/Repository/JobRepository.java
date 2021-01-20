package com.example.hrapp.hrapp.Repository;


import com.example.hrapp.hrapp.Domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

}
