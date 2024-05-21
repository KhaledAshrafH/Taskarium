package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteRequestDto;
import com.springmvc.taskarium.model.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    NoteDto addNote(NoteRequestDto note,Long taskId,String taskName);
    List<NoteDto> getNotesByTaskId(Long taskId);
    void deleteNote(Long noteId) throws Exception;
    List<NoteDto> getAllNotes();
}
