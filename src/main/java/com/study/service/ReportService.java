package com.study.service;

import com.alibaba.fastjson.JSONObject;
import com.study.domain.Report;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReportService {

    int submitRateSorce(@Param("cname")String cname, @Param("uid")int uid, @Param("data")JSONObject data,@Param("timestamp1") Timestamp timestamp);

    ArrayList<Report> testIfRate(@Param("cname")String cname, @Param("uid")int uid, @Param("nowTime")Timestamp nowTime);
}
