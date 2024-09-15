package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.*;
import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.model.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note toEntity(NoteDto noteDto);
    Note toEntity(NoteCreationDto noteDto);
    Note toEntity(NoteUpdateDto noteDto);

    NoteDto toDTO(Note note);
    List<NoteDto> toDTOs(List<Note> notes);
}
