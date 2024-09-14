package com.springmvc.taskarium.controller;

import com.springmvc.taskarium.model.dto.*;
import com.springmvc.taskarium.model.enums.TaskStatus;
import com.springmvc.taskarium.model.mapper.TaskMapper;
import com.springmvc.taskarium.service.NoteService;
import com.springmvc.taskarium.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final NoteService noteService;


    @PostMapping("save-task")
    public String addTask(@ModelAttribute(name = "task") TaskRequestDto task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }


    @GetMapping("tasks")
    public String getTasks(Model model) {
        List<TaskResponseDto> allTasks=taskService.getAllTasks();
        model.addAttribute("tasks", allTasks);
        return "tasks";
    }

    @GetMapping("task/{id}")
    public String getTask(@PathVariable(name = "id") Long id, Model model) {
        TaskDto task = taskService.findTaskById(id);
        List<NoteDto> notes = noteService.getNotesByTaskId(id);
        model.addAttribute("task", task);
        model.addAttribute("notes", notes);
        return "task_details";
    }

    @PostMapping("update/{id}")
    public String editTask(@ModelAttribute(name = "task") updateTaskDto task, @PathVariable(name = "id") Long id) {
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/task/"+task.getId();
    }


}
