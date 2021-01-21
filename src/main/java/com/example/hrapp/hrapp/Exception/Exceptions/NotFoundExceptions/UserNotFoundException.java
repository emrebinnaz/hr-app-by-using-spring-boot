package com.example.hrapp.hrapp.Exception.Exceptions.NotFoundExceptions;


public class UserNotFoundException extends NotFoundException {

   public UserNotFoundException(String errorMessage) {
      super(errorMessage);
   }
}
