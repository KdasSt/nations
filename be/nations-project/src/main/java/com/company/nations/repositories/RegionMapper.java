package com.company.nations.repositories;

import com.company.nations.dto.RegionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("SELECT name, region_id FROM regions ORDER BY region_id ASC")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "region_id")
    }    )
    List<RegionDTO> findAll();
}
