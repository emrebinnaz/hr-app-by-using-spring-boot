package com.example.hrapp.hrapp.Response;


import lombok.Data;

@Data
public class SignupResponse extends AbstractBaseResponse{

    public SignupResponse(boolean success, String message) {
        super(success,message);
    }

}
