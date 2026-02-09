package org.godownManagement.controller;

import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.exceptions.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyExist(UserAlreadyExistException userAlreadyExistException) {

        logger.error("[userAlreadyExist] Throwing exception for : {}", userAlreadyExistException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistException.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        logger.error("[handleValidationException] Throwing exception for : {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchUserExist.class)
    public ResponseEntity<String> noSuchUserExist(NoSuchUserExist noSuchUserExist) {

        logger.error("[noSuchUserExist] Throwing exception for : {}", noSuchUserExist.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchUserExist.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InCorrectPasswordException.class)
    public ResponseEntity<String> inCorrectPasswordException(InCorrectPasswordException inCorrectPasswordException) {

        logger.error("[inCorrectPasswordException] Throwing exception for : {}", inCorrectPasswordException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(inCorrectPasswordException.getMessage());
    }
}
