package com.study.web;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public int test(){
        System.out.println("请求进入");
        return 1;
    }

}
