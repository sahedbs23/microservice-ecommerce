package com.kids.collection.exception;

import com.kids.collection.response.ErrorResponse;
import com.kids.collection.response.ValidationErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
        Logger logger  = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult =  e.getBindingResult();

        Map<String, String> errors = new HashMap<>();
        for (FieldError error: bindingResult.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ValidationErrorResponse response = new ValidationErrorResponse("Validation exception", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMediaNotSupportedException(HttpMediaTypeNotSupportedException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), "Only application/json is supported!");
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse response = new ErrorResponse(e.getMessage(),"Internal server error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
