package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityRequest {

    @Valid
    @NotEmpty(message = "Customer city is required")
    String city;

    @Valid
    @NotEmpty(message = "Customer state is required")
    String state;
}
