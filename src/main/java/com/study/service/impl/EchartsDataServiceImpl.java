package com.study.service.impl;

import com.study.bean.CourtFirstIndexSorce;
import com.study.bean.LatestCourt;
import com.study.bean.OneCourtSorce;
import com.study.domain.City;
import com.study.mapper.CityMapper;
import com.study.mapper.CourtMapper;
import com.study.mapper.EchartsDataMapper;
import com.study.mapper.ReportMapper;
import com.study.service.EchartsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class EchartsDataServiceImpl implements EchartsDataService {
    @Autowired
    CityMapper cityMapper;
    @Autowired
    CourtMapper courtMapper;
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    EchartsDataMapper echartsDataMapper;

    @Override
    public HashMap<String, Double> getCityRank(Timestamp timestamp1, Timestamp timestamp2) {
        /*1.获取城市*/
        List<City> cities = cityMapper.selectAll();
        /*2.获取城市中对应的法院*/
        HashMap<String, Double> map = new HashMap<>();
        for(int i=0;i<cities.size();i++){
            List<String> courtNames = courtMapper.selectByCity(cities.get(i).getCityname());
            double sorce = 0;
            for(int j=0;j<courtNames.size();j++){
                String court = courtNames.get(j);
                OneCourtSorce oneCourtSorce = echartsDataMapper.selectByCourtTime(court, timestamp1, timestamp2);
                if(oneCourtSorce != null){
                    String number = reportMapper.countUser(court, timestamp1, timestamp2);
                    sorce = sorce + oneCourtSorce.getEndSorce()/Integer.parseInt(number);
                }else{
                    continue;
                }

                /*String number = reportMapper.countUser(court, timestamp1, timestamp2);
                sorce = sorce + oneCourtSorce.getEndSorce()/Integer.parseInt(number);*/
            }
            sorce = sorce / courtNames.size();
            map.put(cities.get(i).getCityname(),sorce);
        }
        return map;
    }

    @Override
    public List<OneCourtSorce> getCourtRank(String city, Timestamp timestamp1, Timestamp timestamp2) {
        List<OneCourtSorce> oneCourtSorces = echartsDataMapper.selectByCityTime(city, timestamp1, timestamp2);
        return oneCourtSorces;
    }

    @Override
    public List<CourtFirstIndexSorce> selectCourtFirstIndexSorceByCity(String city, Timestamp timestamp1, Timestamp timestamp2) {
        List<CourtFirstIndexSorce> courtFirstIndexSorces = echartsDataMapper.selectCourtFirstIndexSorceByCity(city, timestamp1, timestamp2);
        return courtFirstIndexSorces;
    }

    @Override
    public List<LatestCourt> selectLatestCourtByCity(String city) {
        List<LatestCourt> latestCourts = echartsDataMapper.selectLatestCourtByCity(city);
        return latestCourts;
    }
}
