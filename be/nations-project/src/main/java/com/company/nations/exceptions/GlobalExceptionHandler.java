package com.company.nations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

//    // Handle all uncaught exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "Unexpected error occurred");
//        body.put("details", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<Object> handleResourceNotFound(SQLSyntaxErrorException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "invalid parameters");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

//    // Handle validation errors
//    @ExceptionHandler(SQLSyntaxErrorException.class)
//    public ResponseEntity<Object> handleValidationException(SQLSyntaxErrorException ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "Validation failed");
//
//        Map<String, String> fieldErrors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                fieldErrors.put(error.getField(), error.getDefaultMessage())
//        );
//
//        body.put("errors", fieldErrors);
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

}
