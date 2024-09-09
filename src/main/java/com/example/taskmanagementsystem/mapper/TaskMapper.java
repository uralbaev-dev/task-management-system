package com.example.taskmanagementsystem.mapper;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.dto.TaskDto;
import com.example.taskmanagementsystem.domain.enums.Status;

import java.time.LocalDate;

import static com.example.taskmanagementsystem.utils.DateFormatter.*;

/**
 * @className: TaskMapper
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate() != null ? task.getDueDate().toString() : null)
                .status(task.getStatus())
                .build();
    }

    public static Task toEntity(TaskDto taskDto) {
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .dueDate(LocalDate.parse(taskDto.getDueDate(), TIME_FORMATTER))
                .status(Status.OPEN)
                .build();
    }
}
