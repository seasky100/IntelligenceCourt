package com.study.mapper;

import com.study.domain.User;

import java.util.List;

public interface AccountMapper {

    List<User> selectByRid(int rid);

    int updatePassword(User user);

    int insert(User user);

}
