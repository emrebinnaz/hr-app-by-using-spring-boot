package com.example.hrapp.hrapp.Response.Job;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class JobListResponse extends BaseResponse {

    private List<JobDTO> jobDTOList;

    public JobListResponse(boolean success, String message, List<JobDTO> jobDTOList) {

        super(success,message);
        this.jobDTOList = jobDTOList;
    }
}
