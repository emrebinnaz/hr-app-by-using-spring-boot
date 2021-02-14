package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.MapStruct.JobMapper;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Response.Job.JobListResponse;
import com.example.hrapp.hrapp.Response.Job.JobResponse;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final JobMapper jobMapper;

    @PostMapping("/saveJob")
    @PreAuthorize("hasRole('ROLE_HR_MANAGER')")
    public ResponseEntity<BaseResponse> addJob(@RequestBody @Valid JobDTO jobDTO) {
        final Job job = jobMapper.mapToEntity(jobDTO);

        return ResponseEntity.ok(jobService.addJob(job));
    }

    @DeleteMapping("/deleteJob/{jobId}")
    @PreAuthorize("hasRole('ROLE_HR_MANAGER')")
    public ResponseEntity<BaseResponse> deleteJob(@PathVariable final String jobId) {

        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }

    @GetMapping("/getJob/{jobId}")
    public ResponseEntity<JobResponse> getJob(@PathVariable final String jobId) {
        final Job job = jobService.getJob(jobId);
        final JobDTO jobDTO = jobMapper.mapToDto(job);

        return ResponseEntity.ok(new JobResponse(jobDTO,true,"Job is retrieved"));
    }

    @PutMapping("/updateJob/{jobId}")
    @PreAuthorize("hasRole('ROLE_HR_MANAGER')")
    public ResponseEntity<BaseResponse> updateJob(@PathVariable final String jobId,
                                                  @RequestBody @Valid JobDTO jobDTO) {

        return ResponseEntity.ok(jobService.updateJob(jobDTO,jobId));
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<JobListResponse> getAllJob() {
        final List<Job> allJobs = jobService.getAllJobs();
        final List<JobDTO> jobDTOList = jobMapper.mapToDto(allJobs);

        return ResponseEntity.ok(new JobListResponse(true,"Jobs are retrieved", jobDTOList));
    }
}
