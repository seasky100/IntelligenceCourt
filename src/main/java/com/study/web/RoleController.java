package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.ResultMap;
import com.study.domain.Role;
import com.study.service.RoleService;
import com.study.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value="/getRole",method= RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<Role>> getRole(WebRequest req){
        int page = Integer.parseInt(req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit"));
        List<Role> roles = roleService.selectAll();
        return Page.paging(page,limit,roles);
    }

    @RequestMapping(value = "/updateRoleWeight",method = RequestMethod.POST)
    @ResponseBody
    public int updateRoleWeight(@RequestBody JSONObject data){
        Role role = new Role(data.getInteger("rid"), null, data.getString("rname"), data.getDouble("rweight"));
        int res = roleService.updateByPrimaryKey(role);
        return res;
    }

}
