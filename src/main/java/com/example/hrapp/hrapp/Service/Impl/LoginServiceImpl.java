package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.User;
import com.example.hrapp.hrapp.DTO.Request.LoginRequestDTO;
import com.example.hrapp.hrapp.Exception.Exceptions.UserNotFoundException;
import com.example.hrapp.hrapp.Response.LoginResponse;
import com.example.hrapp.hrapp.Service.LoginService;
import com.example.hrapp.hrapp.Util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final AuthenticationProvider authenticationProvider;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Override
    public LoginResponse login(LoginRequestDTO loginRequestDTO) {
        try {
            User user = (User) customUserDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

            var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                                        loginRequestDTO.getPassword());
            Authentication authentication = authenticationProvider.authenticate(authenticationToken);
            String jwtToken =  JwtUtil.generateToken(authentication,secretKey,7);

            return new LoginResponse(jwtToken,true, "Successfull login");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        throw new UserNotFoundException("User not found");
    }
}
