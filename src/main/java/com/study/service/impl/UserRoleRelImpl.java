package com.study.service.impl;

import com.study.domain.UserRoleRel;
import com.study.mapper.UserRoleRelMapper;
import com.study.service.UserRoleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleRelImpl implements UserRoleRelService {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    @Autowired
    UserRoleRelMapper userRoleRelMapper;

    @Override
    public List<UserRoleRel> selectByRid(int rid) {
        List<UserRoleRel> userRoleRels = userRoleRelMapper.selectByRid(rid);
        return userRoleRels;
    }

    @Override
    public int insert(UserRoleRel record) {
        return userRoleRelMapper.insert(record);
    }

    @Override
    public int deleteByUid(int uid) {
        return userRoleRelMapper.deleteByUid(uid);
    }
}
