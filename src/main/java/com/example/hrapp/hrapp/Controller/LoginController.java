package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.DTO.Request.LoginRequestDTO;
import com.example.hrapp.hrapp.Response.LoginResponse;
import com.example.hrapp.hrapp.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {

        return loginService.login(loginRequestDTO);
    }

}
