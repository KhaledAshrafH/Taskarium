package com.springmvc.taskarium.service.impl;

import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskRequestDto;
import com.springmvc.taskarium.model.dto.TaskResponseDto;
import com.springmvc.taskarium.model.dto.updateTaskDto;
import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.enums.TaskStatus;
import com.springmvc.taskarium.model.mapper.TaskMapper;
import com.springmvc.taskarium.repository.TaskRepository;
import com.springmvc.taskarium.service.TaskService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskRequestDto addTask(TaskRequestDto task) {
        log.warn("task is {}", task);
        Task newTask = taskMapper.toEntity(task);
        newTask.setStatus(TaskStatus.ACTIVE);
        log.warn("new task is {}", newTask);
        taskRepository.save(newTask);
        return task;
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAllByStatus(TaskStatus.ACTIVE);
        return taskMapper.toResDtos(tasks);
    }

    @Override
    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return taskMapper.toDto(task);
    }

    @Override
    public void updateTask(updateTaskDto task) {
        Task taskUpdated=taskMapper.toEntity(task);
        log.error("taskUpdated service :: {}", taskUpdated);

        if(taskRepository.findById(taskUpdated.getId()).isPresent()){
            taskRepository.save(taskUpdated);
        }
        else{
            log.error("task is {}", task);
        }
    }


}
