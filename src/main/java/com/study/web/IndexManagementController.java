package com.study.web;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.IndexRelation;
import com.study.bean.ResultMap;
import com.study.bean.ThreeIndexWeight;
import com.study.domain.Firstindex;
import com.study.domain.Secondindex;
import com.study.domain.Thirdindex;
import com.study.service.FirstIndexService;
import com.study.service.IndexManagementService;
import com.study.service.SecondIndexService;
import com.study.service.ThirdIndexService;
import com.study.util.Page;
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
public class IndexManagementController {
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    @Autowired
    IndexManagementService indexManagementService;
    @Autowired
    FirstIndexService firstIndexService;
    @Autowired
    SecondIndexService secondIndexService;
    @Autowired
    ThirdIndexService thirdIndexService;

    @RequestMapping(value = "/getRelation", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<IndexRelation>> getRelation(WebRequest req) {
        List<IndexRelation> list = indexManagementService.getRelation();
        ResultMap<List<IndexRelation>> listResultMap = Page.paging(Integer.parseInt(req.getParameter("page")), Integer.parseInt(req.getParameter("limit")), list);
        return listResultMap;
    }

    @RequestMapping(value = "/getFirstIndex", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<Firstindex>> getFirstIndex(WebRequest req) {
        List<Firstindex> list = firstIndexService.selectAll();
        ResultMap<List<Firstindex>> listResultMap = Page.paging(Integer.parseInt(req.getParameter("page")), Integer.parseInt(req.getParameter("limit")), list);
        return listResultMap;
    }

    @RequestMapping(value = "/getSecondIndex", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<Secondindex>> getSecondIndex(WebRequest req) {
        List<Secondindex> list = secondIndexService.selectAll();
        return Page.paging(Integer.parseInt(req.getParameter("page")), Integer.parseInt(req.getParameter("limit")), list);
    }

    @RequestMapping(value = "/getThirdIndex", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap<List<Thirdindex>> getThirdIndex(WebRequest req) {
        List<Thirdindex> list = thirdIndexService.selectAll();
        return Page.paging(Integer.parseInt(req.getParameter("page")), Integer.parseInt(req.getParameter("limit")), list);
    }

    @RequestMapping(value = "/updateWeight", method = RequestMethod.POST)
    @ResponseBody
    public int updateWeight(@RequestBody JSONObject data) {
        int res = 0;
        if (data.getString("firstIndex") != null && data.getString("secIndex") != null && data.getString("thirIndex") != null) {
            ThreeIndexWeight threeIndexWeight = new ThreeIndexWeight(data.getString("firstIndex"), data.getDouble("firstWeight"), data.getString("secIndex"), data.getDouble("secWeight"), data.getString("thirIndex"), data.getDouble("thirWeight"));
            res = indexManagementService.updateThreeIndexWeight(threeIndexWeight);
        } else if (data.getInteger("fid") != null) {
            Firstindex firstindex = new Firstindex(data.getInteger("fid"), data.getString("firstIndex"), data.getDouble("firstWeight"));
            if(data.getInteger("flag") == 1){
                if(firstIndexService.testRepeat(firstindex.getFname()) == null){
                    res = firstIndexService.updateByPrimaryKey(firstindex);
                }
            }else if(data.getInteger("flag") == 0){
                res = firstIndexService.updateByPrimaryKey(firstindex);
            }
        } else if (data.getInteger("sid") != null) {
            Secondindex secondindex = new Secondindex(data.getInteger("sid"), data.getString("secondIndex"), data.getDouble("secondWeight"), null);
            if(data.getInteger("flag") == 1){
                if(secondIndexService.testRepeat(secondindex.getSname()) == null){
                    res = secondIndexService.updateByPrimaryKey(secondindex);
                }
            }else if(data.getInteger("flag") == 0){
                res = secondIndexService.updateByPrimaryKey(secondindex);
            }
        } else if (data.getInteger("tid") != null) {
            Thirdindex thirdindex = new Thirdindex(data.getInteger("tid"), data.getString("thirdIndex"), data.getDouble("thirdWeight"), null);
            if( data.getInteger("flag") == 1 ){
                if(thirdIndexService.testRepeat(thirdindex.getTname()) == null){
                    res = thirdIndexService.updateByPrimaryKey(thirdindex);
                }
            }else if( data.getInteger("flag") == 0 ){
                res = thirdIndexService.updateByPrimaryKey(thirdindex);
            }
        }
        return res;
    }

    @RequestMapping(value = "/getOneIndexList", method = RequestMethod.GET)
    @ResponseBody
    public <T> List<T> getOneIndexList(WebRequest req) {
        int level = Integer.parseInt(req.getParameter("level"));
        List<T> list = null;
        if (level == 1) {
            list = (List<T>) firstIndexService.selectAll();
        } else if (level == 2) {
            list = (List<T>) secondIndexService.selectAll();
        }
        return list;
    }

    @RequestMapping(value = "/delIndex", method = RequestMethod.POST)
    @ResponseBody
    public int delIndex(WebRequest req) {
        try {
            if (Integer.parseInt(req.getParameter("level")) == 1) {
                List<Secondindex> list = querySecIndex(Integer.parseInt(req.getParameter("id")));
                for (Secondindex sec : list) {
                    thirdIndexService.deleteByParentId(sec.getSid());
                    secondIndexService.deleteByPrimaryKey(sec.getSid());
                }
                firstIndexService.deleteByPrimaryKey(Integer.parseInt(req.getParameter("id")));
            } else if (Integer.parseInt(req.getParameter("level")) == 2) {
                List<Thirdindex> list = queryThirIndex(Integer.parseInt(req.getParameter("id")));
                for (Thirdindex thir : list) {
                    thirdIndexService.deleteByPrimaryKey(thir.getTid());
                }
                secondIndexService.deleteByPrimaryKey(Integer.parseInt(req.getParameter("id")));
            } else if (Integer.parseInt(req.getParameter("level")) == 3) {
                thirdIndexService.deleteByPrimaryKey(Integer.parseInt(req.getParameter("id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }

    @RequestMapping(value = "/addIndex", method = RequestMethod.POST)
    @ResponseBody
    public int addIndex(@RequestBody JSONObject data, WebRequest req) {
        int res = 0;
        if (Integer.parseInt(req.getParameter("level")) == 1) {
            Firstindex firstindex = new Firstindex(null, data.getString("name"), data.getDouble("weight"));
            res = firstIndexService.insert(firstindex);
        } else if (Integer.parseInt(req.getParameter("level")) == 2) {
            Secondindex secondindex = new Secondindex(null, data.getString("name"), data.getDouble("weight"), data.getInteger("parent"));
            res = secondIndexService.insert(secondindex);
        } else if (Integer.parseInt(req.getParameter("level")) == 3) {
            Thirdindex thirdindex = new Thirdindex(null, data.getString("name"), data.getDouble("weight"), data.getInteger("parent"));
            res = thirdIndexService.insert(thirdindex);
        }
        return res;
    }

    /*查询二级子指标*/
    public List<Secondindex> querySecIndex(int fid) {
        List<Secondindex> secondindices = secondIndexService.querySecIndex(fid);
        return secondindices;
    }

    /*查询三级子指标*/
    public List<Thirdindex> queryThirIndex(int sid) {
        List<Thirdindex> thirdindices = thirdIndexService.queryThirIndex(sid);
        return thirdindices;
    }


}
