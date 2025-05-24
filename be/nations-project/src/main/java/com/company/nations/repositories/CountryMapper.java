package com.company.nations.repositories;

import com.company.nations.dto.CountryDTO;
import com.company.nations.models.Country;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface CountryMapper {
    @Select("SELECT * FROM countries WHERE country_id = #{id}")
    Country findById(int id);

    @Select("SELECT * FROM countries")
    List<Country> findAll();

    @Select("SELECT count(*) FROM countries")
    long countAllCountries();


    @Select("SELECT country_id, name, area, country_code2  " +
            "FROM countries " +
            "ORDER BY ${sort} " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    @Results({
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "area", column = "area"),
            @Result(property = "countryCode2", column = "country_code2")
    })
    List<CountryDTO> findAllCountriesPaged(
            @Param("pageSize") int pageSize,
            @Param("offset") int offset,
            @Param("sort") String sort);

}