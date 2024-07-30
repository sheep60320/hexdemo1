package com.systex.hexdemo1.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {
    private static final String ID_EXCEPTION_ID = "id";

    @ExceptionHandler(com.systex.hexdemo1.exception.IDNotFoundEception.class)
    public String handleIdNotFoundException(com.systex.hexdemo1.exception.IDNotFoundEception e, Model m) {
        m.addAttribute(ID_EXCEPTION_ID, e.getId());
        return "exception/id";
    }
}