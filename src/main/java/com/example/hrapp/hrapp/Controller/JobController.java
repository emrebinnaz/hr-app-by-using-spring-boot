package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Response.Job.JobListResponse;
import com.example.hrapp.hrapp.Response.Job.JobResponse;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/saveJob")
    @PreAuthorize("hasAuthority('WRITE_JOB_PRIVILEGE')")
    public ResponseEntity<BaseResponse> addJob(@RequestBody @Valid JobDTO jobDTO) {

        return ResponseEntity.ok(jobService.addJob(jobDTO));

    }

    @DeleteMapping("/deleteJob/{jobId}")
    @PreAuthorize("hasAuthority('DELETE_JOB_PRIVILEGE')")
    public ResponseEntity<BaseResponse> deleteJob(@PathVariable final String jobId) {

        return ResponseEntity.ok(jobService.deleteJob(jobId));

    }

    @GetMapping("/getJob/{jobId}")
    public ResponseEntity<JobResponse> getJob(@PathVariable final String jobId) {

        return ResponseEntity.ok(jobService.getJob(jobId));
    }

    @PutMapping("/updateJob/{jobId}")
    @PreAuthorize("hasAuthority('UPDATE_JOB_PRIVILEGE')")
    public ResponseEntity<BaseResponse> updateJob(@PathVariable final String jobId,
                                                  @RequestBody @Valid JobDTO jobDTO) {

        return ResponseEntity.ok(jobService.updateJob(jobDTO,jobId));
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<JobListResponse> getAllJob() {

        return ResponseEntity.ok(jobService.getAllJobs());
    }
}
