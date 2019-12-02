package com.study.mapper;

import com.study.domain.City;
import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(String cityname);

    int insert(City record);

    List<City> selectAll();
}