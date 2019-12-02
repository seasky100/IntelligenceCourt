package com.study.service.impl;

import com.study.bean.IndexRelation;
import com.study.bean.ThreeIndex;
import com.study.bean.ThreeIndexWeight;
import com.study.mapper.IndexManagementMapper;
import com.study.service.IndexManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexManagementImpl implements IndexManagementService {

    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    @Autowired
    IndexManagementMapper indexManagementMapper;

    @Override
    public List<IndexRelation> getRelation() {
        List<IndexRelation> relation = indexManagementMapper.getRelation();
        return relation;
    }

    @Override
    public int updateThreeIndexWeight(ThreeIndexWeight threeIndexWeight) {
        try{
            indexManagementMapper.updateThreeIndexWeight(threeIndexWeight);
        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }

    @Override
    public List<ThreeIndex> getThreeIndex() {

        List<ThreeIndex> threeIndex = indexManagementMapper.getThreeIndex();
        return threeIndex;
    }
}
