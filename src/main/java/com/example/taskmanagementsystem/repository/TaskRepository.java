package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: TaskRepository
 * @date: 06.09.2024
 * @author: Uralbaev Diyorbek
 */

public interface TaskRepository extends JpaRepository<Task, Long> {
}
