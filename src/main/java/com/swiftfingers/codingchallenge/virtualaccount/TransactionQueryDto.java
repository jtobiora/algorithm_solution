package com.swiftfingers.codingchallenge.virtualaccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Obiora on 14-Jul-2025 at 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionQueryDto {
    @JsonProperty("vNUBAN")
    private String nuban;

    private String startDate;
    private String endDate;
}
