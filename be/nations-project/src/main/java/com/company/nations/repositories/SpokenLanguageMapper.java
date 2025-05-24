package com.company.nations.repositories;

import com.company.nations.dto.SpokenLanguageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpokenLanguageMapper {
    @Select("SELECT ll.`language`, cl.official " +
            "FROM country_languages cl " +
            "JOIN languages ll ON cl.language_id = ll.language_id " +
            "WHERE cl.country_id = #{id} " +
            "ORDER BY cl.official DESC ")
    @Results({
            @Result(property = "language", column = "language"),
            @Result(property = "isOfficial", column = "official"),
    })
    List<SpokenLanguageDTO> findSpokenLanguages(int id);
}
