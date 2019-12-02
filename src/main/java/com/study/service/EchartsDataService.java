package com.study.service;

import com.study.bean.CourtFirstIndexSorce;
import com.study.bean.LatestCourt;
import com.study.bean.OneCourtSorce;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public interface EchartsDataService {

    HashMap<String, Double> getCityRank(Timestamp timestamp1, Timestamp timestamp2);//获得城市对应得分

    List<OneCourtSorce>  getCourtRank(String city,Timestamp timestamp1, Timestamp timestamp2);//获得指定地区旗下法院及其得分

    List<CourtFirstIndexSorce> selectCourtFirstIndexSorceByCity(String city,Timestamp timestamp1, Timestamp timestamp2);

    List<LatestCourt> selectLatestCourtByCity(String city);
}
