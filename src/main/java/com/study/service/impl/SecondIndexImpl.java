package com.study.service.impl;

import com.study.domain.Secondindex;
import com.study.mapper.SecondindexMapper;
import com.study.service.SecondIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondIndexImpl implements SecondIndexService {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    @Autowired
    SecondindexMapper secondindexMapper;

    @Override
    public List<Secondindex> selectAll() {
        List<Secondindex> secondindices = secondindexMapper.selectAll();
        return secondindices;
    }

    @Override
    public int insert(Secondindex record) {
        int i = 0;
        if (secondindexMapper.testRepeat(record.getSname()) == null) {
            try {
                i = secondindexMapper.insert(record);
            } catch (Exception e) {
                e.printStackTrace();
                return i;
            }
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Secondindex record) {
        int i = 0;
        try {
            i = secondindexMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer sid) {
        return  secondindexMapper.deleteByPrimaryKey(sid);

    }

    @Override
    public List<Secondindex> querySecIndex(int fid) {
        List<Secondindex> secondindices = secondindexMapper.querySecIndex(fid);
        return secondindices;
    }

    @Override
    public Secondindex testRepeat(String name) {
        Secondindex secondindex = secondindexMapper.testRepeat(name);
        return secondindex;
    }
}
