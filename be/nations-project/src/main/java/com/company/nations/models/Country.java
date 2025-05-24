package com.company.nations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private int countryId;
    private String name;
    private double area;
    private String nationalDay;
    private String countryCode2;
    private String countryCode3;
    private int regionId;
}