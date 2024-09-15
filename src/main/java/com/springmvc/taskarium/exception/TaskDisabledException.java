package com.springmvc.taskarium.exception;

public class TaskDisabledException extends RuntimeException {
    public TaskDisabledException(String message) {
        super(message);
    }
}
