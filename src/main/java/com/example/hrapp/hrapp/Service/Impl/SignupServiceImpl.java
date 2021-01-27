package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.Request.HrManagerSignupRequestDTO;

import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Domain.Role;
import com.example.hrapp.hrapp.Exception.Exceptions.NotUniqueException;
import com.example.hrapp.hrapp.Response.SignupResponse;
import com.example.hrapp.hrapp.Service.RoleService;
import com.example.hrapp.hrapp.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    private final CustomUserDetailsServiceImpl userDetailsService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public SignupResponse signUpAsHrManager(final HrManagerSignupRequestDTO hrManagerSignupRequest) {

        if(userDetailsService.isUserExists(hrManagerSignupRequest.getUsername())) {

            throw new NotUniqueException("Please enter another username");
        }

        final HrManager hrManager = modelMapper.map(hrManagerSignupRequest, HrManager.class);
        final Role roleHrManager = roleService.findByName("ROLE_HR_MANAGER");

        hrManager.setRoles(Arrays.asList(roleHrManager));
        hrManager.setPassword(passwordEncoder.encode(hrManagerSignupRequest.getPassword()));

        userDetailsService.saveUser(hrManager);

        return new SignupResponse(true, "Signup successfull");
    }

}