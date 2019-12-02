package com.study.service;

import com.study.domain.Secondindex;

import java.util.List;

public interface SecondIndexService {

    List<Secondindex> selectAll();

    int insert(Secondindex record);

    int updateByPrimaryKey(Secondindex record);

    int deleteByPrimaryKey(Integer sid);

    List<Secondindex> querySecIndex(int fid);

    Secondindex testRepeat(String name);

}
