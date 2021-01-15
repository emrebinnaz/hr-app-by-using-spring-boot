package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.Request.HrManagerSignupRequestDTO;
import com.example.hrapp.hrapp.Response.SignupResponse;
import com.example.hrapp.hrapp.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody
                                                     @Valid HrManagerSignupRequestDTO hrManagerSignupRequestDTO) {

        final SignupResponse signupResponse = signupService.signUpAsHrManager(hrManagerSignupRequestDTO);

        return ResponseEntity.ok(signupResponse);
    }

}
