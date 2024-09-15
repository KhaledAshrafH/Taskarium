package com.springmvc.taskarium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilityController {
    @GetMapping("")
    public String index() {
        return "home";
    }

    @GetMapping("about")
    String about() {
        return "about";
    }





}
