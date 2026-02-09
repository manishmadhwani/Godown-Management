package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequest {
    @Valid
    @NotEmpty(message = "Customer userName is required")
    String userName;

    @Valid
    @NotNull(message = "Customer contactNo is required")
    @Pattern(regexp = "\\d{10}", message = "Number input is wrong")
    String contactNo;
}
