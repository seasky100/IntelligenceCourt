package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.ThreeIndex;
import com.study.bean.queryCourtStatus;
import com.study.domain.Court;
import com.study.service.CourtService;
import com.study.service.IndexManagementService;
import com.study.service.ReportService;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class RateController {
    @Autowired
    CourtService courtService;
    @Autowired
    IndexManagementService indexManagementService;
    @Autowired
    ReportService reportService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/getCourt",method= RequestMethod.POST)
    @ResponseBody
    public queryCourtStatus getCourt(WebRequest req){
        Court court = courtService.selectByName(req.getParameter("court"));
        queryCourtStatus queryCourtStatus = new queryCourtStatus();
        if(court == null){
            queryCourtStatus.setStatus(0);
            return queryCourtStatus;
        }else if(court != null){
            queryCourtStatus.setName(court.getCname());
            queryCourtStatus.setStatus(1);
        }
        return queryCourtStatus;
    }

    @RequestMapping(value="/getIndexLevel",method=RequestMethod.POST)
    @ResponseBody
    public List<ThreeIndex> getIndexLevel(){

        List<ThreeIndex> threeIndex = indexManagementService.getThreeIndex();

        return threeIndex;
    }

    @RequestMapping(value="/submitRateSorce",method=RequestMethod.POST)
    @ResponseBody
    public int submitRateSorce(@RequestBody JSONObject data,WebRequest req){
        String cname = req.getParameter("court");
        int uid = Integer.parseInt(req.getParameter("uid"));
        Date date = new Date();
        int res = reportService.submitRateSorce(cname, uid, data, new Timestamp(date.getTime()));
        return res;
    }

    @RequestMapping(value = "/getUid",method = RequestMethod.POST)
    @ResponseBody
    public String getUid(WebRequest req){
        String uid = userService.selectUid(req.getParameter("username"));
        System.out.println(req.getParameter("username"));
        return uid;
    }

}
