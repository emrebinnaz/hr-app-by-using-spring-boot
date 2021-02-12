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
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final JobService jobService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @SneakyThrows
    public BaseResponse applyToJob(final JobApplicationDTO jobApplicationDTO,
                                   final MultipartFile resume,
                                   final String jobId) {

        final Job job = jobService.getJob(jobId);
        if(isLastApplicationDatePassed(job.getLastApplicationDate())) {

            throw new ExpiredJobApplicationException("Last application date of job is passed");
        }
        final JobApplication jobApplication = modelMapper.map(jobApplicationDTO, JobApplication.class);
        jobApplication.setApplicantResume(resume.getBytes());
        jobApplication.setJob(job);
        jobApplicationRepository.save(jobApplication);

        return new BaseResponse(true, "Job application successful !");
    }

    @Override
    @Transactional
    public List<JobApplication> getAllJobApplicationsBy(final String jobId) {

        final Job job = jobService.getJob(jobId);

        return jobApplicationRepository.findAllByJob(job);
    }

    @Override
    public List<JobApplication> getAllJobApplications() {

        return jobApplicationRepository.findAll();
    }

    public boolean isLastApplicationDatePassed(final LocalDate lastApplicationDate) {

        return lastApplicationDate.compareTo(java.time.LocalDate.now()) < 0;
    }


}
