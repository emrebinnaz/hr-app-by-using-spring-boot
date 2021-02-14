package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Exception.Exceptions.ExpiredJobApplicationException;
import com.example.hrapp.hrapp.Exception.Exceptions.NotFoundException;
import com.example.hrapp.hrapp.Exception.Exceptions.WrongFileFormatException;
import com.example.hrapp.hrapp.Repository.JobApplicationRepository;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.JobApplicationService;
import com.example.hrapp.hrapp.Service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobService jobService;

    @Override
    @Transactional
    @SneakyThrows
    public BaseResponse applyToJob(final JobApplication jobApplication,
                                   final MultipartFile resume,
                                   final String jobId) {
        final Job job = jobService.getJob(jobId);
        if(isLastApplicationDatePassed(job.getLastApplicationDate())) {

            throw new ExpiredJobApplicationException("Last application date of job is passed");
        }

        if(!isFilePdf(resume.getOriginalFilename())) {

            throw new WrongFileFormatException("Please upload pdf file");
        }

        jobApplication.setApplicantResume(resume.getBytes());
        jobApplication.setJob(job);
        jobApplicationRepository.save(jobApplication);

        return new BaseResponse(true, "Job application successful !");
    }

    private boolean isFilePdf(final String fileName) {
        Pattern pdfCheck = Pattern.compile("^.*\\.pdf$", Pattern.CASE_INSENSITIVE);

        return pdfCheck.matcher(fileName).find();
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

    @Override
    public JobApplication getJobApplicationBy(final String jobApplicationId) {
        final UUID uuid = UUID.fromString(jobApplicationId);
        if(!jobApplicationRepository.existsById(uuid)) {

            throw new NotFoundException("Job Application is not found");
        }
        final Optional<JobApplication> optionalJobApplication = jobApplicationRepository.findById(uuid);

        return optionalJobApplication.get();
    }

    public boolean isLastApplicationDatePassed(final LocalDate lastApplicationDate) {

        return lastApplicationDate.compareTo(java.time.LocalDate.now()) < 0;
    }


}
