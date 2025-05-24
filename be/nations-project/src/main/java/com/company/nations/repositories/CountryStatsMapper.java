package com.company.nations.repositories;

import com.company.nations.dto.CountryStatsDTO;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountryStatsMapper {
//    @Select("WITH ratio_stats AS ( " +
//            "   SELECT a.country_id, a.name, a.country_code3, b.year, b.population, b.gdp, b.gdp / NULLIF(b.population, 0) AS gdp_per_capita " +
//            "FROM countries a " +
//            "JOIN country_stats b ON b.country_id = a.country_id " +
////            "AND a.country_id=1 " +
//            "), " +
//            "max_ratio AS ( " +
//            "   SELECT country_id, MAX(gdp_per_capita) AS max_gdp_per_capita " +
//            "FROM ratio_stats " +
//            "GROUP BY country_id " +
//            ") " +
//            "SELECT r.name, r.country_code3, r.year, r.population, r.gdp " +
//            "FROM ratio_stats r " +
//            "JOIN max_ratio m ON r.country_id = m.country_id AND r.gdp_per_capita = m.max_gdp_per_capita " +
//            "ORDER BY ${sort} "  +
//            "LIMIT #{pageSize} OFFSET #{offset} ")
    @Select("WITH ratio_stats AS ( " +
            "   SELECT " +
            "       a.country_id, " +
            "       a.name, " +
            "       a.country_code3, " +
            "       b.year, " +
            "       b.population, " +
            "       b.gdp, " +
            "       CASE " +
            "           WHEN b.population IS NOT NULL AND b.population != 0 THEN b.gdp / b.population " +
            "           ELSE NULL " +
            "       END AS gdp_per_capita " +
            "   FROM countries a " +
            "   LEFT JOIN country_stats b ON b.country_id = a.country_id " +
            "), " +
            "max_ratio AS ( " +
            "   SELECT " +
            "       country_id, " +
            "       MAX(gdp_per_capita) AS max_gdp_per_capita " +
            "   FROM ratio_stats " +
            "   GROUP BY country_id " +
            ") " +
            "SELECT " +
            "   r.name, " +
            "   r.country_code3, " +
            "   r.year, " +
            "   r.population, " +
            "   r.gdp " +
            "FROM ratio_stats r " +
            "LEFT JOIN max_ratio m ON r.country_id = m.country_id AND r.gdp_per_capita = m.max_gdp_per_capita " +
            "WHERE m.country_id IS NOT NULL OR r.year IS NULL " +
            "ORDER BY ${sort} "  +
            "LIMIT #{pageSize} OFFSET #{offset} ")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "countryCode3", column = "country_code3"),
            @Result(property = "year", column = "year"),
            @Result(property = "population", column = "population"),
            @Result(property = "gdp", column = "gdp")
    })
    List<CountryStatsDTO> findCountriesMaxGdpPerCapita(
            @Param("pageSize") int pageSize,
            @Param("offset") int offset,
            @Param("sort") String sort);
}
