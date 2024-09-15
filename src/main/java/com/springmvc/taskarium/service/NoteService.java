package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteCreationDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
public interface NoteService {
    NoteDto addNote(NoteCreationDto note);
    List<NoteDto> getNotesByTaskId(Long taskId);
    void deleteNote(Long noteId) throws Exception;
    List<NoteDto> getAllNotes();
    NoteDto getNoteById(Long noteId);
    Long getTaskIdByNoteId(Long noteId);
}
