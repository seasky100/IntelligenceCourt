package com.study.service;

import com.study.domain.UserRoleRel;

import java.util.List;

public interface UserRoleRelService {

    List<UserRoleRel> selectByRid(int rid);

    int insert(UserRoleRel record);

    int deleteByUid(int uid);
}
