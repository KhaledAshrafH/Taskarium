package com.springmvc.taskarium.model.dto;


import com.springmvc.taskarium.model.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class updateTaskDto {
    private Long id;
    private String name;
    private String description;
    private String color;
    private TaskStatus status;
}
