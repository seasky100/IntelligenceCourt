package com.study.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    /*
    * @param yyyy-mm
    * */
    public static Timestamp dateFormat(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date dateTime = null;
        Timestamp timeStamp = null;
        try {
            dateTime = simpleDateFormat.parse(time);
            timeStamp = new Timestamp(dateTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

}
