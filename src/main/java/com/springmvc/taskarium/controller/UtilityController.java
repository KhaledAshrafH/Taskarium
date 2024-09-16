package com.springmvc.taskarium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller handles general utility requests such as the home page, about page, and error page.
 */
@Controller
public class UtilityController {

    /**
     * Renders the home page.
     *
     * @return The name of the home page view.
     */
    @GetMapping("")
    public String index() {
        return "home";
    }

    /**
     * Renders the about page.
     *
     * @return The name of the about page view.
     */
    @GetMapping("about")
    String about() {
        return "about";
    }

    /**
     * Renders the error page.
     *
     * @return The name of the error page view.
     */
    @GetMapping("error")
    String error() {
        return "error";
    }

}
