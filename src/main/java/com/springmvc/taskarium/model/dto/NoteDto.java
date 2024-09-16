package com.springmvc.taskarium.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDto {
    private Long noteId;
    private String title;
    private String description;
    private Long taskId;
    private String taskName;
    private LocalDateTime createdAt;
}
