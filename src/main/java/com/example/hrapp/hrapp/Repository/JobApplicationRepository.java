package com.example.hrapp.hrapp.Repository;

import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {

    List<JobApplication> findAllByJob(Job job);


}
