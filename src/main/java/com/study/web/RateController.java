package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.ThreeIndex;
import com.study.bean.queryCourtStatus;
import com.study.domain.Court;
import com.study.service.CourtService;
import com.study.service.IndexManagementService;
import com.study.service.ReportService;
import com.study.service.UserService;
import com.study.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Calendar;
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

        /*判断是否已经对这个法院评分过，以当前提交时月份为区间，查看这个月是否评分过，该月份以前不管*/
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH )+1);
        String str_time = year + "-" + month;
        Timestamp nowTime = DateFormat.dateFormat(str_time);
        if( reportService.testIfRate(cname,uid,nowTime).size() != 0){
            System.out.println(reportService.testIfRate(cname,uid,nowTime).toString());
            return -1;
        }

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
