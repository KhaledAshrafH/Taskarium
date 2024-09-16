package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.TaskCreationDto;
import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskUpdateDto;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * This interface defines the operations related to task management.
 */
@Transactional
public interface TaskService {
    void addTask(TaskCreationDto task);

    List<TaskDto> getAllTasks();

    TaskDto findTaskById(Long id);

    void updateTask(TaskUpdateDto task);

    void deleteTask(Long id);
}
