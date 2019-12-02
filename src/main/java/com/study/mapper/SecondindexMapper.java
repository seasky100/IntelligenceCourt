package com.study.mapper;

import com.study.domain.Secondindex;
import java.util.List;

public interface SecondindexMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Secondindex record);

    Secondindex selectByPrimaryKey(Integer sid);

    List<Secondindex> selectAll();

    int updateByPrimaryKey(Secondindex record);

    Secondindex testRepeat(String name);

    List<Secondindex> querySecIndex(int fid);
}