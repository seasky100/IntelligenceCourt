package com.study.service.impl;

import com.study.domain.User;
import com.study.mapper.UserMapper;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<String> getRolesByUid(int uid) {
        ArrayList<String> rolesByUid = userMapper.getRolesByUid(uid);
        return rolesByUid;
    }

    @Override
    public ArrayList<String> getPermissionByUid(int uid) {
        ArrayList<String> permissionByUid = userMapper.getPermissionByUid(uid);
        return permissionByUid;
    }
}
