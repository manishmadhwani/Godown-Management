package org.godownManagement.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    String comodity;
    String markaName;
    int packing;
    String addressFrom;
}
