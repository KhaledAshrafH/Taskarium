package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.NoteCreationDto;
import com.springmvc.taskarium.model.dto.NoteDto;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * This interface defines the operations related to note management.
 */
@Transactional
public interface NoteService {
    NoteDto addNote(NoteCreationDto note);

    List<NoteDto> getNotesByTaskId(Long taskId);

    void deleteNote(Long noteId) throws Exception;

    List<NoteDto> getAllNotes();

    NoteDto getNoteById(Long noteId);

    Long getTaskIdByNoteId(Long noteId);
}
