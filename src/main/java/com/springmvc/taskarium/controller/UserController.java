package com.springmvc.taskarium.controller;


import com.springmvc.taskarium.exception.UsernameAlreadyExistsException;
import com.springmvc.taskarium.model.dto.UserLoginDto;
import com.springmvc.taskarium.model.dto.UserRegistrationDto;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * This controller handles user registration and login functionalities.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * Renders the user registration form.
     *
     * @return The name of the view for user registration.
     */
    @GetMapping("/register")
    String registerForm() {
        return "register";
    }

    /**
     * Handles user registration submission.
     *
     * @param user           User registration details.
     * @param bindingResult Binding result object for validation errors.
     * @return The name of the view to redirect to on success or failure.
     */
    @PostMapping("register")
    String registerUser(@ModelAttribute(name = "user") UserRegistrationDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";

        try {
            userService.registerUser(user);
            return "redirect:/login";
        }
        catch (UsernameAlreadyExistsException e) {
            bindingResult.rejectValue("username", "error.username", "Username already exists");
            return "register";
        }
    }

    /**
     * Renders the user login form.
     *
     * @return The name of the view for user login.
     */
    @GetMapping("/login")
    String loginForm() {
        return "login";
    }

    /**
     * Handles user login submission.
     *
     * @param user Login credentials.
     * @return The name of the view to redirect to on success or failure.
     */
    @PostMapping("/login")
    String login(@ModelAttribute(name = "user") UserLoginDto user) {
        try {
            userService.loginUser(user);
            return "redirect:/";
        }
        catch (BadCredentialsException e) {
            return "login?error=bad_credentials";
        }
        catch (Exception e) {
            log.error("Login failed: ", e);
            return "login?error=unexpected error";
        }
    }


}
