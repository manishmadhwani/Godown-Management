package org.godownManagement.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryResponse {
    int noOfPackings;
    int entryValuation;
    Date entryDate;
    ItemResponse itemResponse;
}
