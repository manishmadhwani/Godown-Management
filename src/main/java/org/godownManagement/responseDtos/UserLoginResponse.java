package org.godownManagement.responseDtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginResponse {

    String userName;
    Long contactNo;
}