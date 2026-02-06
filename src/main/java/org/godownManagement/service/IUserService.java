package org.godownManagement.service;

import org.godownManagement.dto.UserRegisterRequest;
import org.godownManagement.entities.User;

public interface IUserService {

    boolean registerUser(UserRegisterRequest user);

    boolean validateUser(User user);

    boolean checkIfUserExist(UserRegisterRequest user);
}
