package org.godownManagement.controller;

import org.godownManagement.dto.UserRegisterRequest;
import org.godownManagement.exceptions.UserAlreadyExistException;
import org.godownManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.godownManagement.constants.Constants.USER_CREATED;
import static org.godownManagement.constants.ExceptionConstant.USER_ALREADY_EXIST;

@RestController
public class LoginController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest)
            throws UserAlreadyExistException {
        if (iUserService.checkIfUserExist(userRegisterRequest)) {
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }
        boolean registerUser = iUserService.registerUser(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(USER_CREATED);
    }
}
