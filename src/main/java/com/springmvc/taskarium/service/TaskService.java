package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskRequestDto;
import com.springmvc.taskarium.model.dto.TaskResponseDto;
import com.springmvc.taskarium.model.dto.updateTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    TaskRequestDto addTask(TaskRequestDto task);
    List<TaskResponseDto> getAllTasks();
    TaskDto findTaskById(Long id);
    void updateTask(updateTaskDto task);
}
