package com.study.mapper;

import com.study.bean.IndexRelation;
import com.study.bean.ThreeIndex;
import com.study.bean.ThreeIndexWeight;

import java.util.List;

public interface IndexManagementMapper {

    List<IndexRelation> getRelation();

    int updateThreeIndexWeight(ThreeIndexWeight threeIndexWeight);

    List<ThreeIndex> getThreeIndex();
}
