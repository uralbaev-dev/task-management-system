package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.domain.dto.TaskDto;

import java.util.List;

/**
 * @className: TaskService
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

public interface TaskService {

    List<TaskDto> getTasks();

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);

}
