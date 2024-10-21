package dev.jlkeesh.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@ControllerAdvice("dev.jlkeesh")
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView emptyResult(HttpServletRequest req, EmptyResultDataAccessException e) {
        String requestURI = req.getRequestURI();
        ModelAndView modelAndView = new ModelAndView("/error/404");
        modelAndView.addObject("error_message", "book not found: " + requestURI);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView internal(Exception e) {
        ModelAndView modelAndView = new ModelAndView("/error/500");
        modelAndView.addObject("error_message", "internal error");
        return modelAndView;
    }

}
