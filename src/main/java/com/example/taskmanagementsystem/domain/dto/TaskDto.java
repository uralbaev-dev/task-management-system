package com.example.taskmanagementsystem.domain.dto;

import com.example.taskmanagementsystem.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @className: TaskDto
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {

    private Long id;

    @NotBlank
    @NotNull(message = "Title is null")
    private String title;

    private String description;

    private String dueDate;

    private Status status;

    public TaskDto(String title, String description, String dueDate, Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
}
