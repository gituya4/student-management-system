package com.example.studentapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * GlobalControllerAdvice
 * Intercepts all requests to add metadata to every Thymeleaf template.
 * Specifically used to provide the current URI for Navigation "Active" state.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Injects the current request URI into every model automatically.
     */
    @ModelAttribute("currentUri")
    public String currentUri(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
