package com.springmvc.taskarium.controller;

import com.springmvc.taskarium.exception.UserNotFoundException;
import com.springmvc.taskarium.model.dto.UserDto;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This controller handles administrative tasks related to users.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @param model The model object used to store data for the view.
     * @return The name of the view that displays the list of users.
     */
    @GetMapping("users")
    public String users(Model model) {
        try {
            List<UserDto> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "users";
        }
        catch (UserNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            return "error";
        }
    }
}
