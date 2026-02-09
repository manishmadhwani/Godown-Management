package org.godownManagement.util;

import org.godownManagement.requestDtos.UserLoginRequest;
import org.godownManagement.responseDtos.UserLoginResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
/*
* to do :- create a session and save in the session map, when user logs in
*
* */
@Component
public class SessionManagementUtil {

    HashMap<String, String> session;

    SessionManagementUtil() {
        session = new HashMap<>();
    }

    public boolean checkIfUserIsAlreadyLoggedI(UserLoginRequest userLoginRequest) {
        return true;
    }

    public void saveSession (UserLoginRequest userLoginRequest) {

    }
}
