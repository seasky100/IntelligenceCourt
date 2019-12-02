package com.study.service;

import com.study.domain.Firstindex;

import java.util.List;

public interface FirstIndexService {

    List<Firstindex> selectAll();

    int updateByPrimaryKey(Firstindex record);

    int deleteByPrimaryKey(Integer fid);

    int insert(Firstindex record);

    Firstindex testRepeat(String name);

}
