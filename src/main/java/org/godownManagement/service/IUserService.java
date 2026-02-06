package org.godownManagement.service;

import org.godownManagement.dto.UserLoginRequest;
import org.godownManagement.dto.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;

public interface IUserService {

    User registerUser(UserRegisterRequest user);

    boolean validateUser(UserLoginRequest userLoginRequest) throws NoSuchUserExist, InCorrectPasswordException;

    boolean checkIfUserExist(UserRegisterRequest user);
}
