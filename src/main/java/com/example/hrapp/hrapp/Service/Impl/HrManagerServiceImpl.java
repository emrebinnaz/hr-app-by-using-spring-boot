package com.example.hrapp.hrapp.Service.Impl;


import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Repository.HrManagerRepository;
import com.example.hrapp.hrapp.Service.HrManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HrManagerServiceImpl implements HrManagerService {

    private final HrManagerRepository hrManagerRepository;

    @Override
    public HrManager getCurrentHrManagerBy(final String username) {

        final HrManager hrManager = hrManagerRepository.findByUsername(username);

        return hrManager;
    }
}
