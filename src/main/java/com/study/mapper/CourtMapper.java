package com.study.mapper;

import com.study.domain.Court;
import java.util.List;

public interface CourtMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Court record);

    Court selectByPrimaryKey(Integer cid);

    List<Court> selectAll();

    Court selectByName(String name);

    int updateByPrimaryKey(Court record);

    List<String> selectByCity(String city);
}