package com.springmvc.taskarium.service.impl;

import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteRequestDto;
import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.model.mapper.NoteMapper;
import com.springmvc.taskarium.repository.NoteRepository;
import com.springmvc.taskarium.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDto addNote(NoteRequestDto note, Long taskId, String taskName) {
        Note newNote = noteMapper.toNote(note);
        newNote.setTaskId(taskId);
        newNote.setTaskName(taskName);
        Note savedNote = noteRepository.save(newNote);
        return noteMapper.toNoteDto(savedNote);
    }

    @Override
    public List<NoteDto> getNotesByTaskId(Long taskId) {
        List<Note> notes = noteRepository.findAllByTaskIdOrderByNoteIdDesc(taskId);
        return noteMapper.toNoteDtoList(notes);
    }

    @Override
    public void deleteNote(Long noteId) throws Exception {
        if(noteRepository.findById(noteId).isPresent())
            noteRepository.deleteById(noteId);

        else
            throw new Exception("Id Not found");
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        notes.sort((o1, o2) -> o1.getNoteId()<=o2.getNoteId() ? 0 : -1);
        return noteMapper.toNoteDtoList(notes);
    }
}
