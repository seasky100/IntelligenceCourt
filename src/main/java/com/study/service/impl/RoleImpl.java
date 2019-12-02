package com.study.service.impl;

import com.study.domain.Role;
import com.study.mapper.RoleMapper;
import com.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImpl implements RoleService {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        try{
            roleMapper.updateByPrimaryKey(record);
        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }
}
