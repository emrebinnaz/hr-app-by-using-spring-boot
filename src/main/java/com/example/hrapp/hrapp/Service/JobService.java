package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;

public interface JobService {

    BaseResponse addJob(JobDTO jobDTO);
}
