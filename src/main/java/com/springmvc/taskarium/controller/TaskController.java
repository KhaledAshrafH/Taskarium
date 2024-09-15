package com.springmvc.taskarium.controller;

import com.springmvc.taskarium.model.dto.*;
import com.springmvc.taskarium.service.NoteService;
import com.springmvc.taskarium.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;

    @PostMapping("add-task")
    public String addTask(@ModelAttribute(name = "taskCreationDto") TaskCreationDto taskCreationDto) {
        taskService.addTask(taskCreationDto);
        return "redirect:/tasks";
    }


    @GetMapping("")
    public String getTasks(Model model) {
        List<TaskDto> tasks=taskService.getAllTasks();
        model.addAttribute("allTasks", tasks);
        return "tasks";
    }

    @GetMapping("{taskId}")
    public String getTask(@PathVariable(name = "taskId") Long taskId, Model model) {
        TaskDto task = taskService.findTaskById(taskId);
        List<NoteDto> notes = noteService.getNotesByTaskId(taskId);
        model.addAttribute("task", task);
        model.addAttribute("notes", notes);
        return "task_details";
    }


    @PostMapping("edit-task/{taskId}")
    public String editTask(@ModelAttribute(name = "taskUpdateDto") TaskUpdateDto taskUpdateDto, @PathVariable(name = "taskId") Long taskId) {
        taskUpdateDto.setTaskId(taskId);
        taskService.updateTask(taskUpdateDto);
        return "redirect:/tasks/"+taskUpdateDto.getTaskId();
    }

    @GetMapping("edit-task/{taskId}")
    public String editTaskForm(@PathVariable(name = "taskId") Long taskId,Model model) {
        model.addAttribute("task", taskService.findTaskById(taskId));
        return "edit_task";
    }

    @PostMapping("delete-task/{taskId}")
    public void addTask(@PathVariable(name = "taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }


}
