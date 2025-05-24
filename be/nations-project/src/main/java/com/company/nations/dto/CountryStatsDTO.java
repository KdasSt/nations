package com.company.nations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryStatsDTO {
    private String name;
    private String countryCode3;
    private int year;
    private long population;
    private long gdp;

}