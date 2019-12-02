package com.study.service.impl;

import com.study.domain.Thirdindex;
import com.study.mapper.ThirdindexMapper;
import com.study.service.ThirdIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.smartcardio.SunPCSC;

import java.util.List;

@Service
public class ThirdIndexImpl implements ThirdIndexService {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    @Autowired
    ThirdindexMapper thirdindexMapper;

    @Override
    public List<Thirdindex> selectAll() {
        List<Thirdindex> thirdindices = thirdindexMapper.selectAll();
        return thirdindices;
    }

    @Override
    public int updateByPrimaryKey(Thirdindex record) {
        int i = 0;
        try {
            i = thirdindexMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int insert(Thirdindex record) {
        int i = 0;
        if (thirdindexMapper.testRepeat(record.getTname()) == null) {
            try {
                i = thirdindexMapper.insert(record);
            } catch (Exception e) {
                e.printStackTrace();
                return i;
            }
        }
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer tid) {
        return thirdindexMapper.deleteByPrimaryKey(tid);
    }

    @Override
    public int deleteByParentId(int sid) {
        return thirdindexMapper.deleteByParentId(sid);
    }

    @Override
    public List<Thirdindex> queryThirIndex(int sid) {
        List<Thirdindex> thirdindices = thirdindexMapper.queryThirIndex(sid);
        return thirdindices;
    }

    @Override
    public Thirdindex testRepeat(String name) {
        Thirdindex thirdindex = thirdindexMapper.testRepeat(name);
        return thirdindex;
    }

}
