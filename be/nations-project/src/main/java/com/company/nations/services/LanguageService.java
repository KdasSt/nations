package com.company.nations.services;

import com.company.nations.dto.SpokenLanguageDTO;
import com.company.nations.repositories.SpokenLanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private SpokenLanguageMapper spokenLanguageMapper;

    public List<SpokenLanguageDTO> findSpokenLanguages(int id) {
        return spokenLanguageMapper.findSpokenLanguages(id);
    }
}
