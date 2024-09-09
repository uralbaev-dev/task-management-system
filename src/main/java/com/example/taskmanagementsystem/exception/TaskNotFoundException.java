package com.example.taskmanagementsystem.exception;

/**
 * @className: TaskNotFoundException
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task with id: " + id  + " is not found");
    }
}
