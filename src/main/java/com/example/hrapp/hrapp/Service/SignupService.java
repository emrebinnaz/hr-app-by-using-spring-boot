package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.Request.HrManagerSignupRequestDTO;
import com.example.hrapp.hrapp.Response.SignupResponse;

public interface SignupService {

    SignupResponse signUpAsHrManager(HrManagerSignupRequestDTO hrManagerSignupRequest);
}
