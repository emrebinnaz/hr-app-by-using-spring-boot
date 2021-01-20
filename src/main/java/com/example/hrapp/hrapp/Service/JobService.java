package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Response.Job.JobListResponse;
import com.example.hrapp.hrapp.Response.Job.JobResponse;

public interface JobService {

    BaseResponse addJob(JobDTO jobDTO);

    BaseResponse deleteJob(String id);

    JobResponse getJob(String id);

    BaseResponse updateJob(JobDTO jobDTO, String id);

    JobListResponse getAllJobs();
}
