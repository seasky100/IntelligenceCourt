package com.study.mapper;

import com.study.bean.ThreeIndexWeight;
import com.study.domain.Firstindex;
import java.util.List;

public interface FirstindexMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Firstindex record);

    Firstindex selectByPrimaryKey(Integer fid);

    List<Firstindex> selectAll();

    int updateByPrimaryKey(Firstindex record);

    /*验证是否指标重复*/
    Firstindex testRepeat(String name);

}