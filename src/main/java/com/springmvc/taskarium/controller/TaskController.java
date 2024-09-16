package com.springmvc.taskarium.controller;

import com.springmvc.taskarium.exception.NoteNotFoundException;
import com.springmvc.taskarium.exception.TaskNotFoundException;
import com.springmvc.taskarium.model.dto.NoteDto;
import com.springmvc.taskarium.model.dto.TaskCreationDto;
import com.springmvc.taskarium.model.dto.TaskDto;
import com.springmvc.taskarium.model.dto.TaskUpdateDto;
import com.springmvc.taskarium.service.NoteService;
import com.springmvc.taskarium.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles functionalities related to tasks.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;

    /**
     * Creates a new task.
     *
     * @param taskCreationDto The details of the task to be created.
     * @return A redirect to the tasks list page.
     */
    @PostMapping("add-task")
    public String addTask(@ModelAttribute(name = "taskCreationDto") TaskCreationDto taskCreationDto) {
        try {
            taskService.addTask(taskCreationDto);
            return "redirect:/tasks";
        } catch (Exception e) {
            log.error("Failed to create task");
            return "error";
        }
    }

    /**
     * Retrieves all existing tasks.
     *
     * @param model The model object used to store data for the view.
     * @return The name of the view that displays all tasks.
     */
    @GetMapping("")
    public String getTasks(Model model) {
        try {
            List<TaskDto> tasks = taskService.getAllTasks();
            model.addAttribute("allTasks", tasks);
            return "tasks";
        } catch (Exception e) {
            log.error("Failed to retrieve tasks");
            return "error";
        }
    }

    /**
     * Fetches details of a specific task by its ID.
     *
     * @param taskId The ID of the task to retrieve details for.
     * @param model  The model object used to store data for the view.
     * @return The name of the view that displays the task details.
     */
    @GetMapping("{taskId}")
    public String getTask(@PathVariable(name = "taskId") Long taskId, Model model) {
        try {
            TaskDto task = taskService.findTaskById(taskId);
            List<NoteDto> notes = noteService.getNotesByTaskId(taskId);
            model.addAttribute("task", task);
            model.addAttribute("notes", notes);
            return "task_details";
        }
        catch (TaskNotFoundException e) {
            log.error("Failed to retrieve task");
            return "error";
        }
        catch (NoteNotFoundException e) {
            log.error("Failed to retrieve notes for task");
            return "error";
        }
    }

    /**
     * Updates an existing task.
     *
     * @param taskUpdateDto The details of the task to be updated.
     * @param taskId        The ID of the task to update.
     * @return A redirect to the updated task details page.
     */
    @PostMapping("edit-task/{taskId}")
    public String editTask(@ModelAttribute(name = "taskUpdateDto") TaskUpdateDto taskUpdateDto, @PathVariable(name = "taskId") Long taskId) {
        try {
            taskUpdateDto.setTaskId(taskId);
            taskService.updateTask(taskUpdateDto);
            return "redirect:/tasks/" + taskUpdateDto.getTaskId();
        } catch (TaskNotFoundException e) {
            log.error("Failed to update task");
            return "error";
        }
    }

    /**
     * Displays the form to edit a task.
     *
     * @param taskId The ID of the task to edit.
     * @param model  The model object used to store data for the view.
     * @return The name of the view for editing a task.
     */
    @GetMapping("edit-task/{taskId}")
    public String editTaskForm(@PathVariable(name = "taskId") Long taskId, Model model) {
        try {
            model.addAttribute("task", taskService.findTaskById(taskId));
            return "edit_task";
        }
        catch (TaskNotFoundException e) {
            log.error("Failed to retrieve task for editing");
            return "error";
        }
    }


    /**
     * Deletes a task by its ID.
     *
     * @param taskId The ID of the task to delete.
     */
    @PostMapping("delete-task/{taskId}")
    public void deleteTask(@PathVariable(name = "taskId") Long taskId) {
        try {
            taskService.deleteTask(taskId);
        }
        catch (TaskNotFoundException e) {
            log.error("Failed to delete task");
        }
    }

}
