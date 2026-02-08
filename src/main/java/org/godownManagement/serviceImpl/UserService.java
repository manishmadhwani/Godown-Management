package org.godownManagement.serviceImpl;

import org.godownManagement.requestDtos.UserLoginRequest;
import org.godownManagement.requestDtos.UserRegisterRequest;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.InCorrectPasswordException;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.repository.UserRespository;
import org.godownManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

import static org.godownManagement.constants.ExceptionConstant.INCORRECT_PASSWORD_PROVIDED;
import static org.godownManagement.constants.ExceptionConstant.NO_SUCH_USER_EXIST;

public class UserService implements IUserService {
    @Autowired
    UserRespository userRespository;

    @Override
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .userName(userRegisterRequest.getUserName())
                .contactNo(userRegisterRequest.getContactNo())
                .password(userRegisterRequest.getPassword())
                .godowns(null)
                .items(null)
                .build();
        userRespository.save(user);
        return user;
    }

    @Override
    public User validateUser(UserLoginRequest userLoginRequest) throws NoSuchUserExist, InCorrectPasswordException {
        Optional<User> user = userRespository.getUserByContactNo(userLoginRequest.getContactNo());
        if (user.isEmpty()) throw new NoSuchUserExist(NO_SUCH_USER_EXIST);

        if (!user.get().getPassword().equals(userLoginRequest.getPassword()))
            throw new InCorrectPasswordException(INCORRECT_PASSWORD_PROVIDED);
        return user.get();
    }

    @Override
    public boolean checkIfUserExist(UserRegisterRequest user) {
        return !Objects.isNull(userRespository.getUserByContactNo(user.getContactNo()));
    }
}
