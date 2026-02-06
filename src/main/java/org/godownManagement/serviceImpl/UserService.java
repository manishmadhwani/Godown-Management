package org.godownManagement.serviceImpl;

import org.godownManagement.dto.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.repository.UserRespository;
import org.godownManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService {
    @Autowired
    UserRespository userRespository;

    @Override
    public boolean registerUser(UserRegisterRequest user) {
        return false;
    }

    @Override
    public boolean validateUser(User user) {
        return false;
    }

    @Override
    public boolean checkIfUserExist(UserRegisterRequest user) {
        return false;
    }
}
