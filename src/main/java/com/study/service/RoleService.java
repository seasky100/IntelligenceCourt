package com.study.service;

import com.study.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

}
