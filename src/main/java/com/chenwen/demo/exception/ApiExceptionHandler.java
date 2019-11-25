package com.chenwen.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestException e) {
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC+8"));
        ApiException apiException = new ApiException(e.getMessage(), badRequest, now);
        // 2. Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }
}
