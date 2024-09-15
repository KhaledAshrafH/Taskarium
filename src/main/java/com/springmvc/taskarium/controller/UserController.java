package com.springmvc.taskarium.controller;


import com.springmvc.taskarium.exception.UsernameAlreadyExistsException;
import com.springmvc.taskarium.model.dto.UserLoginDto;
import com.springmvc.taskarium.model.dto.UserRegistrationDto;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    String registerForm(){
        return "register";
    }

    @PostMapping("register")
    String registerUser(@ModelAttribute(name = "user") UserRegistrationDto user, BindingResult bindingResult){
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


    @GetMapping("/login")
    String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    String loginUser(@ModelAttribute(name = "user") UserLoginDto user){
        log.warn("Login From Controller");
        try {
            userService.loginUser(user);
            return "redirect:/";
        }
        catch (BadCredentialsException e) {
            return "login?error=bad_credentials";
        }
        catch (Exception e) {
            return "login?error";
        }
    }

    @GetMapping("/user")
    public UserDetails getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
