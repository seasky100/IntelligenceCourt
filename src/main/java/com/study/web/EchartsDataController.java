package com.study.web;
import com.study.bean.CourtFirstIndexSorce;
import com.study.bean.LatestCourt;
import com.study.bean.OneCourtSorce;
import com.study.domain.Firstindex;
import com.study.service.EchartsDataService;
import com.study.service.FirstIndexService;
import com.study.util.DateFormat;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class EchartsDataController {
    @Autowired
    EchartsDataService echartsDataService;
    @Autowired
    FirstIndexService firstIndexService;

    @RequestMapping(value = "/getCityRank", method = RequestMethod.POST)
    @ResponseBody
    /*
     * 获得城市排名模块所需要数据
     * */
    public HashMap<String, Double> getCityRank(@RequestBody JSONObject data) {
        int num = subtract(data);
        HashMap<String, Double> cityRank = new HashMap<>();
        Timestamp timestamp1 = DateFormat.dateFormat(data.getString("start"));
        Timestamp timestamp2 = DateFormat.dateFormat(data.getString("end"));
        HashMap<String, Double> map = echartsDataService.getCityRank(timestamp1, timestamp2);
        Iterator map1it = map.entrySet().iterator();
        while (map1it.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) map1it.next();
            cityRank.put(entry.getKey(), Double.parseDouble(String.format("%.2f", entry.getValue() / num)));
        }
        System.out.println("看看num="+num);
        return cityRank;
    }

    @RequestMapping(value = "/getCourtRank", method = RequestMethod.POST)
    @ResponseBody
    public List<OneCourtSorce> getCourtRank(@RequestBody JSONObject data, WebRequest req) {
        /*int num=Integer.parseInt(data.getString("end").split("-")[1])-Integer.parseInt(data.getString("start").split("-")[1]);*/
        int num = subtract(data);
        Timestamp timestamp1 = DateFormat.dateFormat(data.getString("start"));
        Timestamp timestamp2 = DateFormat.dateFormat(data.getString("end"));
        List<OneCourtSorce> OneCourtSorces = echartsDataService.getCourtRank(req.getParameter("city"), timestamp1, timestamp2);
        List<OneCourtSorce> res = new ArrayList<>();
        Iterator<OneCourtSorce> iter = OneCourtSorces.iterator();
        while (iter.hasNext()) {
            OneCourtSorce s = iter.next();
            double t = s.getEndSorce() / num;
            s.setEndSorce(Double.parseDouble(String.format("%.2f", t)));
            res.add(s);
        }
        System.out.println("看看num="+num);
        return res;
    }

    @RequestMapping(value = "/getFirstIndex",method=RequestMethod.POST)
    @ResponseBody
    public List<Firstindex> getFirstIndex(){
        List<Firstindex> firstindices = firstIndexService.selectAll();
        return firstindices;
    }

    @RequestMapping(value = "/getFirstIndexSorce",method=RequestMethod.POST)
    @ResponseBody
    public ArrayList<CourtFirstIndexSorce> getFirstIndexSorce(@RequestBody JSONObject data, WebRequest req){
        int num = subtract(data);
        Timestamp timestamp1 = DateFormat.dateFormat(data.getString("start"));
        Timestamp timestamp2 = DateFormat.dateFormat(data.getString("end"));
        List<CourtFirstIndexSorce> list = echartsDataService.selectCourtFirstIndexSorceByCity(req.getParameter("city"), timestamp1, timestamp2);
        ArrayList<CourtFirstIndexSorce> res = new ArrayList<>();
        Iterator<CourtFirstIndexSorce> iterator = list.iterator();
        while(iterator.hasNext()){
            CourtFirstIndexSorce c = iterator.next();
            double t = c.getSorce()/num;
            c.setSorce(Double.parseDouble(String.format("%.2f", t)));
            res.add(c);
        }
        return res;
    }

    @RequestMapping(value = "/getLatestCourt",method = RequestMethod.POST)
    @ResponseBody
    public List<LatestCourt> getLatestCourt(WebRequest req){
        List<LatestCourt> res = echartsDataService.selectLatestCourtByCity(req.getParameter("city"));
        return res;
    }

    /*计算时间差，相差多少个月*/
    public int subtract(JSONObject data) {
        System.out.println(data.getString("start"));
        System.out.println(data.getString("end"));
        int end_year = Integer.parseInt(data.getString("end").split("-")[0]);
        int start_year = Integer.parseInt(data.getString("start").split("-")[0]);
        int end_mon = Integer.parseInt(data.getString("end").split("-")[1]);
        int start_mon = Integer.parseInt(data.getString("start").split("-")[1]);
        int sub_year = 0;
        int sub_mon = 0;
        int res_mon = 0;
        if (end_year > start_year) {
            sub_year = end_year - start_year;
            sub_mon = end_mon + 12 - start_mon;
            res_mon = 12 * (sub_year - 1) + sub_mon;
        }
        if (end_year == start_year) {
            sub_mon = end_mon - start_mon;
            res_mon = sub_mon;
        }
        return res_mon;
    }
}
