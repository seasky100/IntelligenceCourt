package com.study.service;

import com.study.domain.User;

import java.util.List;

public interface AccountService {

    List<User> selectByRid(int rid);

    int updatePassword(User user);

    int insert(User user);
}
