package com.springmvc.taskarium.controller;


import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteRequestDto;
import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping("add-note/{taskName}/{taskId}")
    public String addNote(@ModelAttribute(name = "noteDto") NoteRequestDto noteDto, @PathVariable Long taskId, @PathVariable String taskName) {
        log.warn("Add note contrleerrrrr : {}", noteDto);
        noteService.addNote(noteDto,taskId,taskName);
        return "redirect:/task/"+taskId;
    }

    @GetMapping("delete-note/{taskId}/{noteId}")
    public String deleteNote(@PathVariable Long taskId, @PathVariable Long noteId,@RequestParam String source) throws Exception {
        try {
            noteService.deleteNote(noteId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(source.equals("task"))
            return "redirect:/task/"+taskId;
        else
            return "redirect:/notes";
    }

    @GetMapping("")
    String getAllNotes(Model model) {
        List<NoteDto> notes = noteService.getAllNotes();
        log.warn("from controller {}", notes.get(0));
        model.addAttribute("notes",notes);
        return "notes";
    }
}
