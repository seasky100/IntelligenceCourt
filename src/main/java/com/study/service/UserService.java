package com.study.service;

import com.study.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    /*根据用户名查找账号*/
    User selectByUserName(String username);

    int deleteByPrimaryKey(Integer uid);

    String selectUid(String username);

    ArrayList<String> getRolesByUid(int uid);

    ArrayList<String> getPermissionByUid(int uid);
}
