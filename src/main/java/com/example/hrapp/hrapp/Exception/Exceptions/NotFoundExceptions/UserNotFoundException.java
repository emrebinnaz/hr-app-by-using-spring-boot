package com.example.hrapp.hrapp.Exception.Exceptions.NotFoundExceptions;

import com.example.hrapp.hrapp.Exception.Exceptions.AbstractBaseException;

public class UserNotFoundException extends AbstractBaseException {

   public UserNotFoundException(String errorMessage) {
      super(errorMessage);
   }
}
