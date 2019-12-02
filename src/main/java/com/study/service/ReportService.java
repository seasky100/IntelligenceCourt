package com.study.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface ReportService {

    int submitRateSorce(@Param("cname")String cname, @Param("uid")int uid, @Param("data")JSONObject data,@Param("timestamp1") Timestamp timestamp);
}
