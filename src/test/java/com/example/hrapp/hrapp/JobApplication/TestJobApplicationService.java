package com.example.hrapp.hrapp.JobApplication;


import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Repository.JobApplicationRepository;
import com.example.hrapp.hrapp.Service.Impl.JobApplicationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestJobApplicationService {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;

}
