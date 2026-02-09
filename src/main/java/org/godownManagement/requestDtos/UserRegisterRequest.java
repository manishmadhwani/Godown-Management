package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class UserRegisterRequest {
    @Valid
    @NotEmpty(message = "Customer userName is required")
    @Length(max = 12, min = 6, message = "username should be min 6 characters and max 12 characters")
    String userName;

    @Valid
    @NotEmpty(message = "Password is required")
    @Length(max = 12, min = 6, message = "Password should be min 6 characters and max 12 characters")
    String password;

    @Valid
    @NotNull(message = "Customer number is required")
    @Pattern(regexp = "\\d{10}", message = "Number input is wrong")
    Long contactNo;

    @Valid
    @NotEmpty(message = "Address is required")
    String address;
}
