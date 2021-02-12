package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.JobApplication;
import com.example.hrapp.hrapp.Exception.Exceptions.NotFoundException;
import com.example.hrapp.hrapp.Service.FileService;
import com.example.hrapp.hrapp.Service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final JobApplicationService jobApplicationService;

    @Override
    @SneakyThrows
    public byte[] getResumeOfJobApplication(final String jobApplicationId) {

        final JobApplication jobApplication = jobApplicationService.getJobApplicationBy(jobApplicationId);
        final byte[] applicantResume = jobApplication.getApplicantResume();
        if(applicantResume == null) {
            throw new NotFoundException("Resume is not found");
        }

        return applicantResume;
    }
}
