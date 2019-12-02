package com.study.service.impl;

import com.study.domain.User;
import com.study.mapper.AccountMapper;
import com.study.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements AccountService {

    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    @Autowired
    AccountMapper accountMapper;
    @Override
    public List<User> selectByRid(int rid) {
        List<User> users = accountMapper.selectByRid(rid);
        return users;
    }

    @Override
    public int updatePassword(User user) {
        try{
            accountMapper.updatePassword(user);
        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }

    @Override
    public int insert(User user) {
        return accountMapper.insert(user);
    }
}
