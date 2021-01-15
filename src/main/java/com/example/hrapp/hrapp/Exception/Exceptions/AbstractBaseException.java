package com.example.hrapp.hrapp.Exception.Exceptions;

import lombok.Data;

@Data
public abstract class AbstractBaseException extends RuntimeException{

    private final String errorMessage;
}
