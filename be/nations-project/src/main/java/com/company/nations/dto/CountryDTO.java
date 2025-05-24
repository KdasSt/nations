package com.company.nations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private int countryId;
    private String name;
    private double area;
    private String countryCode2;
}
