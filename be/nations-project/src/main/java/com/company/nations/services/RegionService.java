package com.company.nations.services;

import com.company.nations.dto.RegionDTO;
import com.company.nations.repositories.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionMapper regionMapper;

    public List<RegionDTO> findAll() {
        return regionMapper.findAll();
    }
}
