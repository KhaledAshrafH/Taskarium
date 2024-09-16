package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.NoteCreationDto;
import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteUpdateDto;
import com.springmvc.taskarium.model.entity.Note;
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
