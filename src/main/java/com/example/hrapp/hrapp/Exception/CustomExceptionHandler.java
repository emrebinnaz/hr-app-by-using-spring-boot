package com.example.hrapp.hrapp.Exception;

import com.example.hrapp.hrapp.Exception.Exceptions.UserNotFoundException;
import com.example.hrapp.hrapp.Exception.Response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity handleUserNotFoundException(UserNotFoundException exception,
                                                            WebRequest webRequest){

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                exception.getErrorMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        String errorMessage = extractErrorMessagesFromException(exception);
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), "Validation Failed.", errorMessage);


        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private String extractErrorMessagesFromException(MethodArgumentNotValidException exception) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        for (FieldError error : errors ) {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        }

        return errorMessage.toString();
    }
}
