package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.DTO.Request.LoginRequestDTO;
import com.example.hrapp.hrapp.Response.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequestDTO loginRequestDTO);

}
