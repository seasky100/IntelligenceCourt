package com.study.mapper;

import com.study.domain.Report;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ReportMapper {
    int insert(Report record);

    List<Report> selectAll();

    String countUser(@Param("court") String court, @Param("timestamp1") Timestamp timestamp1, @Param("timestamp2") Timestamp timestamp2);

    int submitRateSorce(@Param("cname")String cname,@Param("uid")int uid,@Param("tname")String tname,@Param("tsorce")double tsorce,@Param("timestamp") Timestamp timestamp);
}