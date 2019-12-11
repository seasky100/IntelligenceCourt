package com.study.mapper;

import com.study.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

    String selectUid(String username);

    ArrayList<String> getRolesByUid(int uid);

    ArrayList<String> getPermissionByUid(int uid);
}