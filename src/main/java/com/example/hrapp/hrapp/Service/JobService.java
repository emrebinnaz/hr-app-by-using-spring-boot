package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Response.BaseResponse;

import java.util.List;

public interface JobService {

    BaseResponse addJob(JobDTO jobDTO);

    BaseResponse deleteJob(String id);

    Job getJob(String id);

    BaseResponse updateJob(JobDTO jobDTO, String id);

    List<Job> getAllJobs();

}
