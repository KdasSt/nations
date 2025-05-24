package com.company.nations.cotrollers;

import com.company.nations.dto.RegionDTO;
import com.company.nations.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<RegionDTO> findAll() {
        return regionService.findAll();
    }
}
