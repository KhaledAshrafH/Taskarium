package com.springmvc.taskarium.service.impl;

import com.springmvc.taskarium.exception.NoteNotFoundException;
import com.springmvc.taskarium.model.dto.NoteCreationDto;
import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.entity.User;
import com.springmvc.taskarium.model.mapper.NoteMapper;
import com.springmvc.taskarium.model.mapper.TaskMapper;
import com.springmvc.taskarium.repository.NoteRepository;
import com.springmvc.taskarium.service.NoteService;
import com.springmvc.taskarium.service.TaskService;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserService userService;
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public NoteDto addNote(NoteCreationDto noteDto) {
        User user = userService.getCurrentUser();
        Task task = taskMapper.toEntity(taskService.findTaskById(noteDto.getTaskId()));
        Note note = noteMapper.toEntity(noteDto);
        note.setTask(task);
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        log.info("Note created successfully: {}", savedNote);
        return noteMapper.toDTO(savedNote);
    }

    @Override
    public List<NoteDto> getNotesByTaskId(Long taskId) {
        User user = userService.getCurrentUser();
        Task task = taskMapper.toEntity(taskService.findTaskById(taskId));
        List<Note> notes = noteRepository.findAllByTaskAndUserOrderByNoteIdDesc(task, user);
        return noteMapper.toDTOs(notes);
    }

    @Override
    public void deleteNote(Long noteId) throws NoteNotFoundException {
        User user = userService.getCurrentUser();
        if (noteRepository.findByNoteIdAndUser(noteId, user).isPresent()){
            noteRepository.deleteById(noteId);
            log.info("Note deleted successfully: {}", noteId);
        }
        else
            throw new NoteNotFoundException("Note with ID " + noteId + " not found for current user");
    }

    @Override
    public List<NoteDto> getAllNotes() {
        User user = userService.getCurrentUser();
        List<Note> notes = noteRepository.findAllByUser(user);
        notes.sort((o1, o2) -> o2.getNoteId().compareTo(o1.getNoteId()));
        return noteMapper.toDTOs(notes);
    }

    @Override
    public NoteDto getNoteById(Long noteId) {
        User user = userService.getCurrentUser();
        Note note = noteRepository.findByNoteIdAndUser(noteId, user).orElseThrow(() ->
                new NoteNotFoundException("Note Not found"));
        return noteMapper.toDTO(note);
    }

    @Override
    public Long getTaskIdByNoteId(Long noteId) {
        User user = userService.getCurrentUser();
        Note note = noteRepository.findByNoteIdAndUser(noteId, user).orElseThrow(() ->
                new NoteNotFoundException("Note Not found"));
        return note.getTask().getTaskId();
    }
}
