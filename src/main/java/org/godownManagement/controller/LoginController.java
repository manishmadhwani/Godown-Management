package org.godownManagement.controller;

import jakarta.validation.Valid;
import org.godownManagement.dto.UserLoginRequest;
import org.godownManagement.dto.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.exceptions.UserAlreadyExistException;
import org.godownManagement.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.godownManagement.constants.Constants.*;
import static org.godownManagement.constants.ExceptionConstant.USER_ALREADY_EXIST;

@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService iUserService;

    @PostMapping("/register")
    ResponseEntity<String> userRegistration(@RequestBody @Valid UserRegisterRequest userRegisterRequest)
            throws UserAlreadyExistException {
        if (iUserService.checkIfUserExist(userRegisterRequest)) {
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }
        User user = iUserService.registerUser(userRegisterRequest);
        logger.info("User created with userId : {}, userName: {}", user.getUserId(), user.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(USER_CREATED + user.getUserName() + USER_LOGIN);
    }

    @PostMapping("/login")
    ResponseEntity<String> userLogin(@RequestBody @Valid UserLoginRequest userLoginRequest)
            throws NoSuchUserExist, InCorrectPasswordException {
        if (iUserService.validateUser(userLoginRequest)) {
            logger.info("User logged in successfully : {}", userLoginRequest.getContactNo());
            return ResponseEntity.status(HttpStatus.OK).body(USER_LOGGED_IN);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(UNABLE_TO_LOGIN);
    }
}
