package com.company.nations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryStat {
    private int countryId;
    private int year;
    private long population;
    private float gdp;


}