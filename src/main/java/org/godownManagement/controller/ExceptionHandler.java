package org.godownManagement.controller;

import org.godownManagement.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyExist(UserAlreadyExistException userAlreadyExistException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(userAlreadyExistException.getMessage());
    }
}
