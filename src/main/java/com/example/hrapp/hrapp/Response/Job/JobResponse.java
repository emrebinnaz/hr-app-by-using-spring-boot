package com.example.hrapp.hrapp.Response.Job;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;
import lombok.Data;

@Data
public class JobResponse extends BaseResponse {

    private JobDTO jobDTO;

    public JobResponse(final JobDTO jobDTO, final boolean success, final String message) {
        super(success, message);
        this.jobDTO = jobDTO;
    }
}
