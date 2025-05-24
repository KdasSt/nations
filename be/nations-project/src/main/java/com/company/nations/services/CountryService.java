package com.company.nations.services;

import com.company.nations.dto.CountryDTO;
import com.company.nations.dto.CountryStatsDTO;
import com.company.nations.dto.CountryStatsJoinViewDTO;
import com.company.nations.dto.PagedResponse;
import com.company.nations.models.Country;
import com.company.nations.repositories.CountryMapper;
import com.company.nations.repositories.CountryStatsJoinViewMapper;
import com.company.nations.repositories.CountryStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryStatsMapper countryStatsMapper;
    @Autowired
    private CountryStatsJoinViewMapper countryStatsJoinViewMapper;

    private final List<String> AllowedSortCollumn = Arrays.asList("name","area","country_code2");
    private final List<String> AllowedSortCountryStatsCollumn = Arrays.asList("name","country_code3","year","population","gdp");
    private final List<String> AllowedSortRegionStatsCollumn = Arrays.asList("continent_name","region_name", "country_name","year","population","gdp");
    public Country findById(int id) {
        return countryMapper.findById(id);
    }

    public List<Country> findAll() {
        return countryMapper.findAll();
    }

    public PagedResponse<CountryDTO> findAllCountriesPaged(Pageable page) {

        int pageSize = page.getPageSize();
        int offset = page.getPageNumber() * pageSize;

        String sort = page.getSort().stream()
                .filter(order -> AllowedSortCollumn.contains(order.getProperty()))
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));

        List<CountryDTO> countries = countryMapper.findAllCountriesPaged(pageSize, offset, sort);
        long total = countryMapper.countAllCountries();

        return new PagedResponse<>(countries, page.getPageNumber(), pageSize, total);
    }

    public PagedResponse<CountryStatsDTO> findCountriesMaxGdpPerCapita(Pageable page) {
        int pageSize = page.getPageSize();
        int offset = page.getPageNumber() * pageSize;

        String sort = page.getSort().stream()
                .filter(order -> AllowedSortCountryStatsCollumn.contains(order.getProperty()))
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));

        List<CountryStatsDTO> countries = countryStatsMapper.findCountriesMaxGdpPerCapita(pageSize, offset, sort);
        long total = countryMapper.countAllCountries();

        return new PagedResponse<>(countries, page.getPageNumber(), pageSize, total);
    }

    public PagedResponse<CountryStatsJoinViewDTO> getFilteredStats(Integer regionId, Integer dateFrom, Integer dateTo, Pageable page){
        int pageSize = page.getPageSize();
        int offset = page.getPageNumber() * pageSize;

        String sort = page.getSort().stream()
                .filter(order -> AllowedSortRegionStatsCollumn.contains(order.getProperty()))
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));

        List<CountryStatsJoinViewDTO> countries = countryStatsJoinViewMapper.getFilteredStats(regionId, dateFrom, dateTo, pageSize, offset,sort);
        long total = countryStatsJoinViewMapper.countFilteredStats(regionId, dateFrom, dateTo);

        return new PagedResponse<>(countries, page.getPageNumber(), pageSize, total);
    }

}