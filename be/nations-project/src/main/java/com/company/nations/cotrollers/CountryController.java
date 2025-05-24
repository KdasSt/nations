package com.company.nations.cotrollers;


import com.company.nations.dto.CountryDTO;
import com.company.nations.dto.CountryStatsDTO;
import com.company.nations.dto.CountryStatsJoinViewDTO;
import com.company.nations.dto.PagedResponse;
import com.company.nations.models.Country;
import com.company.nations.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    public Country findById(@PathVariable int id) {
        return countryService.findById(id);
    }

    @GetMapping
    public PagedResponse<CountryDTO> findAllCountriesPaged(Pageable page){
        return countryService.findAllCountriesPaged(page);
    }

    @GetMapping("/stats")
    public PagedResponse<CountryStatsDTO> findCountriesMaxGdpPerCapita(Pageable page) {
        return countryService.findCountriesMaxGdpPerCapita(page);
    }

    @GetMapping("/region-stats")
    public PagedResponse<CountryStatsJoinViewDTO> getStats(
            @RequestParam(required = false) Integer regionId,
            @RequestParam(required = false) Integer dateFrom,
            @RequestParam(required = false) Integer dateTo,
            Pageable page) {

        return countryService.getFilteredStats(regionId, dateFrom, dateTo, page);
    }

}