package com.study.mapper;

import com.study.domain.Thirdindex;
import java.util.List;

public interface ThirdindexMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Thirdindex record);

    Thirdindex selectByPrimaryKey(Integer tid);

    List<Thirdindex> selectAll();

    int updateByPrimaryKey(Thirdindex record);
}