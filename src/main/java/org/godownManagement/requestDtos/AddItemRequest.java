package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddItemRequest {

    @Valid
    @NotEmpty(message = "Comodity is required")
    String comodity;

    @Valid
    @NotEmpty(message = "Comodity is required")
    String marka;

    @Valid
    @NotNull(message = "Packing is required")
    int packing;

    @Valid
    @NotEmpty(message = "Comodity is required")
    String addressFrom;

    @Valid
    @NotEmpty(message = "UserRequest is required")
    UserRequest userRequest;
}
