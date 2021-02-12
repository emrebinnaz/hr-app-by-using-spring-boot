package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobApplicationService {

    BaseResponse applyToJob(JobApplicationDTO jobApplicationDTO, MultipartFile resume, String jobId);

    List<JobApplication> getAllJobApplicationsBy(String jobId);

    List <JobApplication> getAllJobApplications();
}
