package com.study.service;

import com.study.domain.User;

public interface UserService {
    /*根据用户名查找账号*/
    User selectByUserName(String username);

    int deleteByPrimaryKey(Integer uid);
}
