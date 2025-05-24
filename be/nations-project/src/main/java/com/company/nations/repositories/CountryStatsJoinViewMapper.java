package com.company.nations.repositories;

import com.company.nations.dto.CountryStatsJoinViewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountryStatsJoinViewMapper {
    @Select("SELECT co.name AS continent_name, r.name AS region_name, c.name AS country_name, s.year, s.population, s.gdp " +
            "FROM country_stats s " +
            "JOIN countries c ON s.country_id = c.country_id " +
            "JOIN regions r ON c.region_id = r.region_id " +
            "JOIN continents co ON r.continent_id = co.continent_id " +
            "WHERE (#{regionId} IS NULL OR r.region_id = #{regionId}) " +
            "AND (#{dateFrom} IS NULL OR s.year >= #{dateFrom})" +
            "AND (#{dateTo} IS NULL OR s.year <= #{dateTo}) " +
            "ORDER BY ${sort} "  +
            "LIMIT #{pageSize} OFFSET #{offset} ")
    @Results({
            @Result(property = "continentName", column = "continent_name"),
            @Result(property = "regionName", column = "region_name"),
            @Result(property = "countryName", column = "country_name"),
            @Result(property = "year", column = "year"),
            @Result(property = "population", column = "population"),
            @Result(property = "gdp", column = "gdp")
    })
    List<CountryStatsJoinViewDTO> getFilteredStats(
            @Param("regionId") Integer regionId,
            @Param("dateFrom") Integer dateFrom,
            @Param("dateTo") Integer dateTo,
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("sort") String sort
    );

    @Select("SELECT COUNT(*) " +
            "FROM country_stats s " +
            "LEFT JOIN countries c ON s.country_id = c.country_id " +
            "LEFT JOIN regions r ON c.region_id = r.region_id " +
            "LEFT JOIN continents co ON r.continent_id = co.continent_id " +
            "WHERE (#{regionId} IS NULL OR r.region_id = #{regionId}) " +
            "  AND (#{yearFrom} IS NULL OR s.year >= #{yearFrom}) " +
            "  AND (#{yearTo} IS NULL OR s.year <= #{yearTo})")
    int countFilteredStats(@Param("regionId") Integer regionId,
                           @Param("yearFrom") Integer yearFrom,
                           @Param("yearTo") Integer yearTo);
}
