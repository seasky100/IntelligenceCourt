package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.ResultMap;
import com.study.domain.User;
import com.study.domain.UserRoleRel;
import com.study.service.AccountService;
import com.study.service.UserRoleRelService;
import com.study.service.UserService;
import com.study.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleRelService userRoleRelService;

    @RequestMapping(value = "/getAccount",method = RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<User>> getAccount(WebRequest req){
        int page = Integer.parseInt(req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit"));
        List<User> role = accountService.selectByRid(Integer.parseInt(req.getParameter("role")));
        ResultMap<List<User>> listResultMap = Page.paging(page, limit, role);
        return listResultMap;
    }

    @RequestMapping(value="/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public int updatePassword(@RequestBody  JSONObject data){
        User user = new User(data.getInteger("uid"), data.getString("username"), data.getString("password"), null, null);
        return accountService.updatePassword(user);
    }

    @RequestMapping(value="/addAccount",method=RequestMethod.POST)
    @ResponseBody
    public int addAccount(@RequestBody JSONObject data,WebRequest req){
        User user = new User(null, data.getString("username"), data.getString("password"), 0, 0);
        /*测试是否用户名重复*/
        try{
            if(userService.selectByUserName(user.getUsername()) == null){
                accountService.insert(user);
                User user1 = userService.selectByUserName(user.getUsername());
                userRoleRelService.insert(new UserRoleRel(user1.getUid(),Integer.parseInt(req.getParameter("role"))));
            }else if(userService.selectByUserName(user.getUsername()) != null){
                return FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }

    @RequestMapping(value = "/delAccount",method=RequestMethod.POST)
    @ResponseBody
    public int delAccount(WebRequest req){
        int uid = Integer.parseInt(req.getParameter("uid"));
        try{
            userRoleRelService.deleteByUid(uid);
            userService.deleteByPrimaryKey(uid);
        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }


}
