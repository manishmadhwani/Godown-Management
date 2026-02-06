package org.godownManagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {

    @Valid
    @NotEmpty(message = "Customer contactNo is required")
    Long contactNo;

    @Valid
    @NotEmpty(message = "Customer password is required")
    String password;
}
