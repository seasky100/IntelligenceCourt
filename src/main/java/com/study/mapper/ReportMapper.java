package com.study.mapper;

import com.study.domain.Report;
import java.util.List;

public interface ReportMapper {
    int insert(Report record);

    List<Report> selectAll();
}