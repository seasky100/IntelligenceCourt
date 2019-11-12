package com.study.mapper;

import com.study.domain.Court;
import java.util.List;

public interface CourtMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Court record);

    Court selectByPrimaryKey(Integer cid);

    List<Court> selectAll();

    int updateByPrimaryKey(Court record);
}