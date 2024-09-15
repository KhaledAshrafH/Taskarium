package com.springmvc.taskarium.model.dto;


import com.springmvc.taskarium.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateDto {
    private Long taskId;
    private String name;
    private String description;
    private String color;
    private TaskStatus status;
}
