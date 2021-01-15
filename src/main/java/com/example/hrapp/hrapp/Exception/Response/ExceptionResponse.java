package com.example.hrapp.hrapp.Exception.Response;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private final Date timestamp;
    private final String message;
    private final String details;

}
