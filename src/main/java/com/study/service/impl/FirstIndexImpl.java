package com.study.service.impl;

import com.study.domain.Firstindex;
import com.study.mapper.FirstindexMapper;
import com.study.service.FirstIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstIndexImpl implements FirstIndexService {
    @Autowired
    FirstindexMapper firstindexMapper;

    @Override
    public List<Firstindex> selectAll() {
        List<Firstindex> firstindices = firstindexMapper.selectAll();
        return firstindices;
    }

    @Override
    public int updateByPrimaryKey(Firstindex record) {
        int i =0;
            try{
                 i = firstindexMapper.updateByPrimaryKey(record);
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
                return i;
            }
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return firstindexMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Firstindex record) {
        int i = 0;
        /*先验证是否指标重复*/
        if(firstindexMapper.testRepeat(record.getFname()) == null){
            try{
                i = firstindexMapper.insert(record);
            }catch(Exception e){
                e.printStackTrace();
                return i;
            }
        }
        return i;
    }

    @Override
    public Firstindex testRepeat(String name) {
        Firstindex firstindex = firstindexMapper.testRepeat(name);
        return firstindex;
    }
}
