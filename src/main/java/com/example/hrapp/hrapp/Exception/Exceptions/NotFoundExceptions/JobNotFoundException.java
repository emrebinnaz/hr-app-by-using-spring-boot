package com.example.hrapp.hrapp.Exception.Exceptions.NotFoundExceptions;

import com.example.hrapp.hrapp.Exception.Exceptions.AbstractBaseException;

public class JobNotFoundException extends AbstractBaseException {

    public JobNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
