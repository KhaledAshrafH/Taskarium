package com.springmvc.taskarium.controller;


import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.NoteCreationDto;
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

    @PostMapping("add-note")
    public String addNote(@ModelAttribute(name = "noteCreationDto") NoteCreationDto noteCreationDto,@RequestParam Long taskId) {
        noteCreationDto.setTaskId(taskId);
        log.warn("From Note Controller {}", noteCreationDto);
        NoteDto noteDto = noteService.addNote(noteCreationDto);
        log.warn("From Note Controller {}", noteDto.getTaskId());
        return "redirect:/tasks/"+noteCreationDto.getTaskId();
    }

    @GetMapping("delete-note/{noteId}")
    public String deleteNote(@PathVariable Long noteId,@RequestParam String source) throws Exception {
        Long taskId = noteService.getTaskIdByNoteId(noteId);
        log.warn("From Note Controller {}", taskId);
        noteService.deleteNote(noteId);
        if(source.equals("task"))
            return "redirect:/tasks/"+taskId;
        else
            return "redirect:/notes";
    }

    @GetMapping("")
    String getAllNotes(Model model) {
        List<NoteDto> notes = noteService.getAllNotes();
        model.addAttribute("allNotes",notes);
        return "notes";
    }
}
