package com.study.mapper;

import com.study.bean.CourtFirstIndexSorce;
import com.study.bean.LatestCourt;
import com.study.bean.OneCourtSorce;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface EchartsDataMapper {

    OneCourtSorce selectByCourtTime(@Param("court")String  court,@Param("timestamp1") Timestamp timestamp1, @Param("timestamp2") Timestamp timestamp2);

    List<OneCourtSorce> selectByCityTime(@Param("city")String  city,@Param("timestamp1") Timestamp timestamp1, @Param("timestamp2") Timestamp timestamp2);

    List<CourtFirstIndexSorce> selectCourtFirstIndexSorceByCity(@Param("city")String  city,@Param("timestamp1") Timestamp timestamp1, @Param("timestamp2") Timestamp timestamp2);

    List<LatestCourt> selectLatestCourtByCity(String city);
}
