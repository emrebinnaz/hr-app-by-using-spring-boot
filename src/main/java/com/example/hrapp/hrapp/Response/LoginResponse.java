package com.example.hrapp.hrapp.Response;


import lombok.Data;

@Data
public class LoginResponse extends AbstractBaseResponse{

    private String jwtToken;


    public LoginResponse(String jwtToken, boolean success, String message) {
        super(success,message);
        this.jwtToken = jwtToken;
    }

    public LoginResponse(boolean success, String message) {
        super(success, message);
    }
}
