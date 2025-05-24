package com.company.nations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpokenLanguageDTO {

    private String language;
    private boolean isOfficial;
}
