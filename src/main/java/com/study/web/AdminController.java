package com.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @RequestMapping(value = "/admin",method= RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/relationOFindex",method=RequestMethod.GET)
    public String relationOFindex(){
        return "relationOFindex";
    }

    @RequestMapping(value = "/firstIndex",method = RequestMethod.GET)
    public String firstIndex(){
        return "firstIndexManagement";
    }

    @RequestMapping(value = "/secondIndex",method = RequestMethod.GET)
    public String secondIndex(){
        return "secondIndexManagement";
    }

    @RequestMapping(value = "/thirdIndex",method=RequestMethod.GET)
    public String thirdIndex(){
        return "thirdIndexManagement";
    }

    @RequestMapping(value = "/staff",method = RequestMethod.GET)
    public String staff(){
        return "staffAccount";
    }

    @RequestMapping(value = "/masses",method = RequestMethod.GET)
    public String masses(){
        return "massesAccount";
    }

    @RequestMapping(value = "/role",method = RequestMethod.GET)
    public String role(){
        return "roleWeight";
    }

    @RequestMapping(value = "/select_court",method = RequestMethod.GET)
    public String select_court(){
        return "rateCourt";
    }
}
