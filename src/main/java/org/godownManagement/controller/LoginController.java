package org.godownManagement.controller;

import jakarta.validation.Valid;
import org.godownManagement.requestDtos.UserLoginRequest;
import org.godownManagement.requestDtos.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.exceptions.UserAlreadyExistException;
import org.godownManagement.responseDtos.UserLoginResponse;
import org.godownManagement.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
    ResponseEntity<Object> userLogin(@RequestBody @Valid UserLoginRequest userLoginRequest)
            throws NoSuchUserExist, InCorrectPasswordException {
        logger.info("User trying to log in : {}", userLoginRequest.getContactNo());
        User user= iUserService.validateUser(userLoginRequest);
        if (Objects.nonNull(user)) {
            UserLoginResponse userLoginResponse = UserLoginResponse.builder().userName(user.getUserName())
                    .contactNo(user.getContactNo())
                    .build();
            logger.info(USER_LOGGED_IN);
            logger.info("User logged in successfully : {}", userLoginRequest.getContactNo());
            return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(UNABLE_TO_LOGIN);
    }
}
