package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {

    @Valid
    @NotNull(message = "Customer contactNo is required")
    @Pattern(regexp = "\\d{10}", message = "Number input is wrong")
    String  contactNo;

    @Valid
    @NotEmpty(message = "Customer password is required")
    String password;
}
