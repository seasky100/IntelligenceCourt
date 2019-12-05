package com.study.service.impl;

import com.study.domain.User;
import com.study.mapper.UserMapper;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByUserName(String username) {

        User user = userMapper.selectByUserName(username);
        return user;
    }

    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public String selectUid(String username) {
        String uid = userMapper.selectUid(username);
        return uid;
    }
}
