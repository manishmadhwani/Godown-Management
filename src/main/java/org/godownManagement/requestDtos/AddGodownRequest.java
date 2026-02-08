package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AddGodownRequest {
    @NonNull
    UserRequest userRequest;

    @Valid
    @NotEmpty(message = "Godown name is required")
    String name;

    @Valid
    @NotEmpty(message = "Godown address is required")
    String address;

    @NonNull
    CityRequest cityRequest;

}
