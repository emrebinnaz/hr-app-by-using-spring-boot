package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.Request.HrManagerSignupRequestDTO;

import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Response.SignupResponse;
import com.example.hrapp.hrapp.Service.RoleService;
import com.example.hrapp.hrapp.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    private final CustomUserDetailsServiceImpl userDetailsService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupResponse signUpAsHrManager(final HrManagerSignupRequestDTO hrManagerSignupRequest) {
        //TODO: Some email validations..

        final HrManager hrManager = new HrManager();

        hrManager.setName(hrManagerSignupRequest.getName());
        hrManager.setSurname(hrManagerSignupRequest.getSurname());
        hrManager.setUsername(hrManagerSignupRequest.getUsername());
        hrManager.setPassword(passwordEncoder.encode(hrManagerSignupRequest.getPassword()));
        hrManager.setAccountNonExpired(true);
        hrManager.setCredentialsNonExpired(true);
        hrManager.setAccountNonLocked(true);
        hrManager.setEnabled(true);
        hrManager.setRoles(roleService.findAllByName("ROLE_HR_MANAGER"));

        //TODO: Create general signup request..

        userDetailsService.saveUser(hrManager);

        return new SignupResponse(true, "Signup successfull");
    }

}
