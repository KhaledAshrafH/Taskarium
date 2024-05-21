package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskRequestDto;
import com.springmvc.taskarium.model.dto.TaskResponseDto;
import com.springmvc.taskarium.model.dto.updateTaskDto;
import com.springmvc.taskarium.model.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);

    List<TaskResponseDto> toResDtos(List<Task> tasks); // Add this method
    TaskRequestDto toReqDto(Task task);
    Task toEntity(TaskRequestDto taskDto);

    List<TaskDto> toDtos(List<Task> tasks);

    List<Task> toEntities(List<TaskDto> taskDtos);
    Task toEntity(updateTaskDto taskDto);
}
