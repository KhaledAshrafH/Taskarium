package com.springmvc.taskarium.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCreationDto {
    private String title;
    private String description;
    private Long taskId;
}
