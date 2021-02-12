package com.example.hrapp.hrapp.Exception;

import com.example.hrapp.hrapp.Exception.Exceptions.ExpiredJobApplicationException;
import com.example.hrapp.hrapp.Exception.Exceptions.NotFoundException;
import com.example.hrapp.hrapp.Exception.Exceptions.NotUniqueException;
import com.example.hrapp.hrapp.Exception.Exceptions.WrongFileFormatException;
import com.example.hrapp.hrapp.Exception.Response.ExceptionResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity handleNotFoundException(NotFoundException exception,
                                                            WebRequest webRequest){

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                exception.getErrorMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ExpiredJobApplicationException.class})
    protected ResponseEntity handleNotFoundException(ExpiredJobApplicationException exception,
                                                     WebRequest webRequest){

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                exception.getErrorMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity handleAccessDeniedException(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                "You cannot access that service",
                request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = {NotUniqueException.class})
    protected ResponseEntity handleNotUniqueUsernameWhenSignUp(NotUniqueException exception,
                                                         WebRequest webRequest){

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                exception.getErrorMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WrongFileFormatException.class})
    protected ResponseEntity handleFileFormat(WrongFileFormatException exception,
                                                               WebRequest webRequest){

        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                exception.getErrorMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        String errorMessage = extractErrorMessagesFromException(exception);
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),
                                                                    errorMessage,
                                                            "Validation failed");


        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private String extractErrorMessagesFromException(MethodArgumentNotValidException exception) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        for (FieldError error : errors ) {
            errorMessage.append(error.getDefaultMessage()).append(". ");
        }

        return errorMessage.toString();
    }

}
