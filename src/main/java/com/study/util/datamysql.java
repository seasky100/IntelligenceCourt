package com.study.util;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class datamysql {

    public static void main1(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql1 ="select cname from court";
        ArrayList<String> courts = new ArrayList<>();
        String sql2="select tname from thirdindex";
        ArrayList<String> tnames = new ArrayList<>();
        String sql3="insert into report(cname,uid,tname,tsorce,time) VALUES(?,?,?,?,?)";


        try{
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql1);
            rs = st.executeQuery();
            while(rs.next()){
                courts.add(rs.getString(1));
            }
            JdbcUtils.release(rs,st,conn);

            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql2);
            rs = st.executeQuery();
            while(rs.next()){
                tnames.add(rs.getString(1));
            }
            JdbcUtils.release(rs,st,conn);

            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql3);
            for(String court:courts){
                for(int j=0;j<7;j++){
                    int uid = j + 1;
                    for(String tname:tnames){
                        double d = 100 - Math.random() * 100;
                        st.setString(1,court);
                        st.setInt(2,uid);
                        st.setString(3,tname);
                        st.setDouble(4,d);
                        Date date = new Date();
                        st.setTimestamp(5,new Timestamp(date.getTime()));
                        st.addBatch();
                    }
                }
            }
            st.executeBatch();
            /*conn.setAutoCommit(false);
            conn.commit();*/
            JdbcUtils.release(rs,st,conn);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void main2(String[] args){
        Date xxx = new Date();
        System.out.println(xxx);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(xxx));

    }
}
