package org.godownManagement.requestDtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddEntryRequestToGodown {
    @Valid
    @NotNull(message = "No of sacks are required")
    int noOfSacks;

    @Valid
    @NotNull
    int costPerSack;

    @Valid
    @NotEmpty(message = "Comodity name is required")
    String comodity;

    @Valid
    @NotEmpty(message = "type is required")
    String type;

    @Valid
    @NotEmpty(message = "Marka name is required")
    String markaName;

    @Valid
    @NotNull(message = "Packing is required")
    int packing;

}
