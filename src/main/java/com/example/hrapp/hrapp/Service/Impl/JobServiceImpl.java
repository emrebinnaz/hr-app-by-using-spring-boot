package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Domain.Job;
import com.example.hrapp.hrapp.Domain.User;
import com.example.hrapp.hrapp.Repository.JobRepository;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.HrManagerService;
import com.example.hrapp.hrapp.Service.JobService;
import com.example.hrapp.hrapp.Util.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

    private String getUsernameOfCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        final String username = authentication.getName();

        return username;
    }
}
