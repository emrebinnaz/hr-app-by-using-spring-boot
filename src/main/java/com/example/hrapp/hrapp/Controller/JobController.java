package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/saveJob")
    @PreAuthorize("hasAuthority('WRITE_JOB_PRIVILEGE')")
    public BaseResponse addJob(@RequestBody @Valid JobDTO jobDTO) {

        return jobService.addJob(jobDTO);
    }
}
