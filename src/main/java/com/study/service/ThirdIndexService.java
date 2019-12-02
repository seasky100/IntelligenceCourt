package com.study.service;

import com.study.domain.Thirdindex;

import java.util.List;

public interface ThirdIndexService {

    List<Thirdindex> selectAll();

    int updateByPrimaryKey(Thirdindex record);

    int insert(Thirdindex record);

    int deleteByPrimaryKey(Integer tid);

    int deleteByParentId(int sid);

    List<Thirdindex> queryThirIndex(int sid);

    Thirdindex testRepeat(String name);

}
