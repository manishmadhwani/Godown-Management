package org.godownManagement.controller;

import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyExist(UserAlreadyExistException userAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchUserExist.class)
    public ResponseEntity<String> noSuchUserExist(NoSuchUserExist noSuchUserExist) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchUserExist.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InCorrectPasswordException.class)
    public ResponseEntity<String> inCorrectPasswordException(InCorrectPasswordException inCorrectPasswordException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(inCorrectPasswordException.getMessage());
    }
}
