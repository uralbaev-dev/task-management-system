package com.example.taskmanagementsystem.handler;


import com.example.taskmanagementsystem.exception.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * This class provides global exception handling for the REST API.
 * It handles various exceptions that can occur during the execution of an operation.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(TaskNotFoundException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(NOT_FOUND.value())
                                .message(exp.getMessage())
                                .code(NOT_FOUND.getReasonPhrase())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = exp.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage()).append(" ");
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(BAD_REQUEST.value())
                                .message(sb.toString())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }
}
