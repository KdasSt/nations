package com.company.nations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryStatsJoinViewDTO {
    private String continentName;
    private String regionName;
    private String countryName;
    private int year;
    private long population;
    private long gdp;
}
