package com.stackroute.userservice.controller;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieExceptionController extends Exception {
    @ExceptionHandler(value = MovieNotFoundGlobalException.class)
    public ResponseEntity<Object> movieNotFoundException() {
        return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = MovieExistsByIdGlobalException.class)
    public ResponseEntity<Object> movieExistsByIdException() {
        return new ResponseEntity<>("Movie already exists", HttpStatus.NOT_FOUND);
    }
}
