package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Exception.Exceptions.NotFoundExceptions.JobNotFoundException;
import com.example.hrapp.hrapp.Repository.JobRepository;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.HrManagerService;
import com.example.hrapp.hrapp.Service.JobService;
import com.example.hrapp.hrapp.Authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final ModelMapper modelMapper;

    private final JobRepository jobRepository;

    private final AuthenticationFacade authenticationFacade;

    private final HrManagerService hrManagerService;

    @Override
    public BaseResponse addJob(final JobDTO jobDTO) {

        final Job job = modelMapper.map(jobDTO, Job.class);
        String username = getUsernameOfCurrentUser();
        HrManager hrManager = hrManagerService.getCurrentHrManagerBy(username);
        job.setHrManager(hrManager);
        jobRepository.save(job);

        return new BaseResponse(true,"Job was added successfully !");
    }

    @Override
    public BaseResponse updateJob(final JobDTO jobDTO, final String id) {

        UUID uuid = UUID.fromString(id);
        final Optional<Job> jobOptional = jobRepository.findById(uuid);
        if(jobOptional.isPresent()) {

            final Job job = jobOptional.get();

            if(!job.isDeleted()) {

                modelMapper.map(jobDTO,job);
                jobRepository.save(job);

                return new BaseResponse(true,"Job has been updated");
            }
            else {
                return new BaseResponse(false, "That job had been already deleted");
            }
        }
        else {
            throw new JobNotFoundException("Job is not found");
        }
    }

    @Override
    public List<Job> getAllJobs() {
        final List<Job> allJobs = jobRepository.findAll();

        return allJobs.stream()
                .filter(job -> !job.isDeleted())
                .collect(Collectors.toList());

    }

    @Override
    public BaseResponse deleteJob(String id) {

        UUID uuid = UUID.fromString(id);

        final Optional<Job> jobOptional = jobRepository.findById(uuid);
        if(jobOptional.isPresent()) {

            final Job job = jobOptional.get();

            if(!job.isDeleted()) {
                job.setDeleted(true);
                jobRepository.save(job);
            }
            else {
                return new BaseResponse(false, "That job had been already deleted");
            }
        }
        else {
            throw new JobNotFoundException("Job is not found");
        }

        return new BaseResponse(true,"Job has been deleted");
    }

    @Override
    public Job getJob(final String id) {

        UUID uuid = UUID.fromString(id);

        if(jobRepository.existsById(uuid)) {

            final Optional<Job> optionalJob = jobRepository.findById(uuid);
            final Job job = optionalJob.get();

            if(job.isDeleted()) {

                throw new JobNotFoundException("Job has been already deleted");
            }

            return job;
        } else {
            throw new JobNotFoundException("Job is not found");
        }

    }

    private String getUsernameOfCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        final String username = authentication.getName();

        return username;
    }
}
