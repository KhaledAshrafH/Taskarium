package com.springmvc.taskarium.service.impl;

import com.springmvc.taskarium.exception.TaskDisabledException;
import com.springmvc.taskarium.exception.TaskNotFoundException;
import com.springmvc.taskarium.model.dto.TaskCreationDto;
import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskUpdateDto;
import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.entity.User;
import com.springmvc.taskarium.model.enums.TaskStatus;
import com.springmvc.taskarium.model.mapper.TaskMapper;
import com.springmvc.taskarium.repository.TaskRepository;
import com.springmvc.taskarium.service.TaskService;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;


    @Override
    public void addTask(TaskCreationDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        User user = userService.getCurrentUser();
        task.setStatus(TaskStatus.ACTIVE);
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        User user = userService.getCurrentUser();
        log.warn("From TaskService : {}", user);
        List<Task> tasks = taskRepository.findAllByStatusAndUser(TaskStatus.ACTIVE, user);
        return taskMapper.toDTOs(tasks);
    }

    @Override
    public TaskDto findTaskById(Long id) {
        User user = userService.getCurrentUser();
        Task task = taskRepository.findByTaskIdAndUser(id, user).orElseThrow(() ->
                new TaskNotFoundException("Task Not Found"));

        if (task.getStatus().equals(TaskStatus.DISABLED))
            throw new TaskDisabledException("Task Disabled");

        return taskMapper.toDTO(task);
    }

    @Override
    public void updateTask(TaskUpdateDto taskDto) {
        User user = userService.getCurrentUser();

        Task task = taskRepository.findByTaskIdAndUser(taskDto.getTaskId(), user).orElseThrow(() ->
                new TaskNotFoundException("Task Not Found"));

        if (task.getStatus().equals(TaskStatus.DISABLED))
            throw new TaskDisabledException("You can't edit this task.This Task Disabled!");
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        User user = userService.getCurrentUser();

        if (taskRepository.findByTaskIdAndUser(taskId, user).isEmpty())
            throw new TaskNotFoundException("Task Not Found");
        taskRepository.deleteById(taskId);
    }


}
