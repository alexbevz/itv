package ru.bevz.itv.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleAnyException(Exception exception, Model model) {

        model.addAttribute("error", exception.getMessage());

        return "error";
    }

}
