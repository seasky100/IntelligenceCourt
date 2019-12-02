package com.study.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.mapper.ReportMapper;
import com.study.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
public class ReportImpl implements ReportService {
    @Autowired
    ReportMapper reportMapper;
    @Override
    public int submitRateSorce(String cname, int uid, JSONObject data,Timestamp timestamp) {

        Set<String> keySet = data.keySet();
        try{
            for(String key : keySet){
                /*System.out.println(key +"="+ data.getDouble(key));*/
                reportMapper.submitRateSorce(cname,uid,key,data.getDouble(key),timestamp);
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
