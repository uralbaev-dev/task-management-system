package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.domain.Task;
import com.example.taskmanagementsystem.domain.dto.TaskDto;
import com.example.taskmanagementsystem.domain.enums.Status;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.taskmanagementsystem.utils.DateFormatter.TIME_FORMATTER;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskManagementSystemApplicationTests {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void whenGetTasks_thenReturnTaskList() {
        List<Task> tasks = List.of(new Task(1L, "Title", "Description", LocalDate.now(), Status.OPEN));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskDto> result = taskService.getTasks();

        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void whenPostTask_thenReturnResult() {
        TaskDto taskDto = new TaskDto("Title", "Description", LocalDate.now().format(TIME_FORMATTER), Status.OPEN);

        Task savedTask = new Task(1L, "Title", "Description", LocalDate.now(), Status.OPEN);

        // Мокаем сохранение задачи в репозитории
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskDto result = taskService.createTask(taskDto);

        // Проверка результата
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
        assertEquals(LocalDate.now().toString(), result.getDueDate());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void whenUpdateTask_thenReturnUpdatedTask() {
        TaskDto updatedTaskDto = new TaskDto("Updated Task", "Updated Description", LocalDate.now().plusDays(2).format(TIME_FORMATTER), Status.IN_PROGRESS);

        Task existingTask = new Task(1L, "Old Task", "Old Description", LocalDate.now(), Status.OPEN);

        Task updatedTask = new Task(1L, "Updated Task", "Updated Description", LocalDate.now().plusDays(2), Status.IN_PROGRESS);

        // Мокаем поиск задачи по ID
        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        // Мокаем сохранение обновленной задачи
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        TaskDto result = taskService.updateTask(1L, updatedTaskDto);

        // Проверка результата
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(Status.IN_PROGRESS, result.getStatus());
        assertEquals(LocalDate.now().plusDays(2).toString(), result.getDueDate());

        verify(taskRepository, times(1)).findById(1L);

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void whenDeleteTask_thenTaskDeleted() {
        Long taskId = 1L;

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
