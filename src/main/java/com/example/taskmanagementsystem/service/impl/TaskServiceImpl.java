package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.dto.TaskDto;
import com.example.taskmanagementsystem.exception.TaskNotFoundException;
import com.example.taskmanagementsystem.mapper.TaskMapper;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.taskmanagementsystem.utils.DateFormatter.TIME_FORMATTER;

/**
 * @className: TaskServiceImpl
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

@Log4j2
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getTasks() {
        log.info("getTasks");
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        log.info("createTask");
        Task task = TaskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        log.info("createTask taskId: {}", savedTask.getId());
        return TaskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto dto) {
        log.info("updateTask taskId: {}", id);
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(dto.getTitle());
                    task.setDescription(dto.getDescription());
                    task.setDueDate(LocalDate.parse(dto.getDueDate(), TIME_FORMATTER));
                    task.setStatus(dto.getStatus() != null ? dto.getStatus() : task.getStatus());
                    Task updatedTask = taskRepository.save(task);
                    return TaskMapper.toDto(updatedTask);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public void deleteTask(Long id) {
        log.info("deleteTask");
        taskRepository.deleteById(id);
    }
}
