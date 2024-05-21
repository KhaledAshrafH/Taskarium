package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteRequestDto;
import com.springmvc.taskarium.model.entity.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note toNote(NoteDto noteDto);

    NoteDto toNoteDto(Note note);
    NoteRequestDto toNoteReqDto(Note note);


    List<Note> toNoteList(List<NoteDto> noteDtoList);
    Note toNote(NoteRequestDto noteRequestDto);
    List<NoteDto> toNoteDtoList(List<Note> noteList);

}
