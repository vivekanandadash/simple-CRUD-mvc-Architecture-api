package com.api_example.exception;

import com.api_example.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandler(
            Exception e,
            WebRequest request
    ){
        ErrorDto errorDto = new ErrorDto(new Date(), e.toString(), request.getDescription(false));

        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }
}
