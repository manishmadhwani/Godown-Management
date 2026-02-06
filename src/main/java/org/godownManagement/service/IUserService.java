package org.godownManagement.service;

import org.godownManagement.entities.User;

public interface IUserService {

    boolean registerUser(User user);

    boolean validateUser(User user);
}
