package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskCreationDto;
import com.springmvc.taskarium.model.dto.TaskUpdateDto;
import com.springmvc.taskarium.model.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskDto taskDto);
    Task toEntity(TaskCreationDto taskDto);
    Task toEntity(TaskUpdateDto taskDto);

    TaskDto toDTO(Task task);
    List<TaskDto> toDTOs(List<Task> tasks);


}
