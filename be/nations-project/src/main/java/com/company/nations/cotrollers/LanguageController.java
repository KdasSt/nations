package com.company.nations.cotrollers;

import com.company.nations.dto.SpokenLanguageDTO;
import com.company.nations.services.LanguageService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<SpokenLanguageDTO> findSpokenLanguages(int id) {
        return languageService.findSpokenLanguages(id);
    }
}