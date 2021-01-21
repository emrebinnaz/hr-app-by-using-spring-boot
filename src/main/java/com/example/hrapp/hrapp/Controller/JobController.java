package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Response.AllJobApplicationsResponse;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Response.Job.JobListResponse;
import com.example.hrapp.hrapp.Response.Job.JobResponse;
import com.example.hrapp.hrapp.Service.JobApplicationService;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    private final ModelMapper modelMapper;

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

        final Job job = jobService.getJob(jobId);
        final JobDTO jobDTO = modelMapper.map(job, JobDTO.class);

        return ResponseEntity.ok(new JobResponse(jobDTO,true,"Job is retrieved"));
    }

    @PutMapping("/updateJob/{jobId}")
    @PreAuthorize("hasAuthority('UPDATE_JOB_PRIVILEGE')")
    public ResponseEntity<BaseResponse> updateJob(@PathVariable final String jobId,
                                                  @RequestBody @Valid JobDTO jobDTO) {

        return ResponseEntity.ok(jobService.updateJob(jobDTO,jobId));
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<JobListResponse> getAllJob() {

        final List<Job> allJobs = jobService.getAllJobs();

        final Type listType = new TypeToken<List<JobDTO>>(){}.getType();
        List<JobDTO> jobDTOList = modelMapper.map(allJobs, listType);

        return ResponseEntity.ok(new JobListResponse(true,"Jobs are retrieved", jobDTOList));
    }

}
