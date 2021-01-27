package com.example.hrapp.hrapp.Exception.Exceptions;

import com.example.hrapp.hrapp.Exception.Exceptions.AbstractBaseException;

public class NotFoundException extends AbstractBaseException {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
