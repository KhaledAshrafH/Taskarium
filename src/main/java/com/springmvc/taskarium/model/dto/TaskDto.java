package com.springmvc.taskarium.model.dto;

import com.springmvc.taskarium.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long taskId;
    private String name;
    private String description;
    private String color;
    private TaskStatus status;
    private List<NoteDto> notes;
    private LocalDateTime createdAt;
}
