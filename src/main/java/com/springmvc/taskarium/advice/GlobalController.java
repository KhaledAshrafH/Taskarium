package com.springmvc.taskarium.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {
    private final HttpServletRequest request;

    @ModelAttribute(name = "urlPath")
    public String getPath(){
        return request.getServletPath();
    }

}



