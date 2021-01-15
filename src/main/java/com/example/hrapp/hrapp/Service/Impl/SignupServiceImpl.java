package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.DTO.Request.BaseSignupRequest;
import com.example.hrapp.hrapp.DTO.Request.HrManagerSignupRequestDTO;
import com.example.hrapp.hrapp.Domain.HrManager;
import com.example.hrapp.hrapp.Domain.User;
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
        //TODO: Some email validations.. like isEmailUnique ?

        final HrManager hrManager = new HrManager();
        fillUserInformations(hrManager, hrManagerSignupRequest);

        hrManager.setRoles(roleService.findAllByName("ROLE_HR_MANAGER"));

        userDetailsService.saveUser(hrManager);

        return new SignupResponse(true, "Signup successfull");
    }

    private void fillUserInformations(final User user, final BaseSignupRequest baseSignupRequest) {

        user.setName(baseSignupRequest.getName());
        user.setSurname(baseSignupRequest.getSurname());
        user.setUsername(baseSignupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(baseSignupRequest.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
    }

}
