package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Response.AllJobApplicationsResponse;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    private final ModelMapper modelMapper;

    @PostMapping("/applyToJob/{jobId}")
    public ResponseEntity<BaseResponse> applyToJob(@RequestBody @Valid JobApplicationDTO jobApplicationDTO,
                                                   @PathVariable final String jobId) {

        return ResponseEntity.ok(jobApplicationService.applyToJob(jobApplicationDTO, jobId));
    }

    @GetMapping("/getAllJobApplicationsBy/{jobId}")
    @PreAuthorize("hasRole('ROLE_HR_MANAGER')")
    public ResponseEntity<AllJobApplicationsResponse> getAllJobApplicationsBy(@PathVariable final String jobId) {

        final List<JobApplication> jobApplications = jobApplicationService.getAllJobApplicationsBy(jobId);

        final Type listType = new TypeToken<List<JobApplicationDTO>>(){}.getType();
        List<JobApplicationDTO> jobApplicationsDtoList = modelMapper.map(jobApplications, listType);

        return ResponseEntity.ok(new AllJobApplicationsResponse("All jobs are retrieved",
                true,
                jobApplicationsDtoList));
    }

    @GetMapping("/getAllJobApplications")
    @PreAuthorize("hasRole('ROLE_HR_MANAGER')")
    public ResponseEntity<AllJobApplicationsResponse> getAllJobApplications() {

        final List<JobApplication> jobApplications = jobApplicationService.getAllJobApplications();

        final Type listType = new TypeToken<List<JobApplicationDTO>>(){}.getType();
        List<JobApplicationDTO> jobApplicationsDtoList = modelMapper.map(jobApplications, listType);

        return ResponseEntity.ok(new AllJobApplicationsResponse("All jobs are retrieved",
                true,
                jobApplicationsDtoList));

    }

}
