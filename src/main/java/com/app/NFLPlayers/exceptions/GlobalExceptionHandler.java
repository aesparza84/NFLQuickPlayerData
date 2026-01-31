package com.app.NFLPlayers.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public String handleTeamNotFound(TeamNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleArgsTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }
}
