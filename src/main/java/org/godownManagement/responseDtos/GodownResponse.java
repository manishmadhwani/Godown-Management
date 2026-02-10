package org.godownManagement.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GodownResponse {
    String godownName;
    String godownAddress;
    int valuation;
    List<EntryResponse> entryResponses;
}
