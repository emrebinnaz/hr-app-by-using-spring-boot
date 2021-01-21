package com.example.hrapp.hrapp.Response;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import lombok.Data;

import java.util.List;

@Data

public class AllJobApplicationsResponse extends BaseResponse{

    List<JobApplicationDTO> jobApplicationDTOList;

    public AllJobApplicationsResponse(String message,
                                      boolean success,
                                      List<JobApplicationDTO> jobApplicationDTOList) {
        super(success,message);
        this.jobApplicationDTOList = jobApplicationDTOList;

    }

}
