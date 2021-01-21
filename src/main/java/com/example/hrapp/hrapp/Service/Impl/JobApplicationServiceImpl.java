package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Exception.Exceptions.ExpiredJobApplicationException;
import com.example.hrapp.hrapp.Repository.JobApplicationRepository;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.JobApplicationService;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final JobService jobService;

    private final ModelMapper modelMapper;

    @Override
    public BaseResponse applyToJob(final JobApplicationDTO jobApplicationDTO,
                                   final String jobId) {

        final Job job = jobService.getJob(jobId);
        if(isLastApplicationDatePassed(job.getLastApplicationDate())) {

            throw new ExpiredJobApplicationException("Last application date of job is passed");
        }
        final JobApplication jobApplication = modelMapper.map(jobApplicationDTO, JobApplication.class);

        jobApplication.setJob(job);
        jobApplicationRepository.save(jobApplication);

        return new BaseResponse(true, "Job application successful !");
    }

    @Override
    public List<JobApplication> getAllJobApplicationsBy(final String jobId) {

        final Job job = jobService.getJob(jobId);

        return jobApplicationRepository.findAllByJob(job);
    }

    public boolean isLastApplicationDatePassed(final LocalDate lastApplicationDate) {

        return lastApplicationDate.compareTo(java.time.LocalDate.now()) < 0;
    }


}
