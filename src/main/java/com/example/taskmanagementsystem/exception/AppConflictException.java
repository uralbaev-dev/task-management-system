package com.example.taskmanagementsystem.exception;

public class AppConflictException extends RuntimeException {
    public AppConflictException(String message) {
        super(message);
    }
}
