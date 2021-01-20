package com.example.hrapp.hrapp.Exception.Response;

import com.example.hrapp.hrapp.Response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse extends BaseResponse {

    private Date timestamp;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {

        super(false,message);
        this.details = details;
        this.timestamp = timestamp;

    }
}
