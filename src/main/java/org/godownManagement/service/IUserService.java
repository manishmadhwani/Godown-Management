package org.godownManagement.service;

import org.godownManagement.requestDtos.UserLoginRequest;
import org.godownManagement.requestDtos.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;

public interface IUserService {

    User registerUser(UserRegisterRequest user);

    User validateUser(UserLoginRequest userLoginRequest) throws NoSuchUserExist, InCorrectPasswordException;

    boolean checkIfUserExist(UserRegisterRequest user);
}
