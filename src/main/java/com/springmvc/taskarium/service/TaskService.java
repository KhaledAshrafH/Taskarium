package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskCreationDto;
import com.springmvc.taskarium.model.dto.TaskUpdateDto;

import java.util.List;


public interface TaskService {
    void addTask(TaskCreationDto task);
    List<TaskDto> getAllTasks();
    TaskDto findTaskById(Long id);
    void updateTask(TaskUpdateDto task);
    void deleteTask(Long id);
}
