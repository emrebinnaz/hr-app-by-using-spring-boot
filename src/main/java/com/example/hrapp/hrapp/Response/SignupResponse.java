package com.example.hrapp.hrapp.Response;


import lombok.Data;

@Data
public class SignupResponse extends BaseResponse{

    public SignupResponse(boolean success, String message) {
        super(success,message);
    }

}
