package com.springmvc.taskarium.controller;


import com.springmvc.taskarium.model.dto.NoteCreationDto;
import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles functionalities related to notes.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("notes")
public class NoteController {
    private final NoteService noteService;


    /**
     * Add a new note to a task.
     *
     * @param noteCreationDto Object containing note details
     * @param taskId          ID of the task to which the note is associated
     * @return Redirect to the task details page
     */
    @PostMapping("add-note")
    public String addNote(@ModelAttribute(name = "noteCreationDto") NoteCreationDto noteCreationDto, @RequestParam Long taskId) {
        noteCreationDto.setTaskId(taskId);
        log.info("Adding a new note: {}", noteCreationDto);
        try {
            NoteDto noteDto = noteService.addNote(noteCreationDto);
            log.info("Added note with task ID: {}", noteDto.getTaskId());
            return "redirect:/tasks/" + noteCreationDto.getTaskId();
        }
        catch (Exception e) {
            log.error("Error adding note: {}", e.getMessage());
            return "error";
        }
    }

    /**
     * Delete a note by its ID.
     *
     * @param noteId ID of the note to delete
     * @param source Source of the request
     * @return Redirect based on the source parameter
     * @throws Exception if there are issues with note deletion
     */

    @GetMapping("delete-note/{noteId}")
    public String deleteNote(@PathVariable Long noteId, @RequestParam String source) throws Exception {
        try {
            Long taskId = noteService.getTaskIdByNoteId(noteId);
            log.info("Deleting note with ID: {}", noteId);
            noteService.deleteNote(noteId);
            return "redirect:" + ("task".equals(source) ? "/tasks/" + taskId : "/notes");
        }
        catch (Exception e) {
            log.error("Error deleting note: {}", e.getMessage());
            return "error";
        }
    }

    /**
     * Get all notes.
     *
     * @param model Model for holding attributes
     * @return View name for displaying all notes
     */
    @GetMapping("")
    public String getAllNotes(Model model) {
        try {
            List<NoteDto> notes = noteService.getAllNotes();
            model.addAttribute("allNotes", notes);
            return "notes";
        }
        catch (Exception e) {
            log.error("Error fetching all notes: {}", e.getMessage());
            return "error";
        }
    }
}
