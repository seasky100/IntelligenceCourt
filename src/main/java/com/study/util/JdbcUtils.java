package com.study.util;


import java.sql.*;

public class JdbcUtils {
    //数据库驱动
    public static final String url = "jdbc:mysql://localhost:3306/intelligencecourt?useSSL=false&&rewriteBatchedStatements=true";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "admin";
    public static final String password = "admin";
    //静态代码块（类加载时会执行此代码）

    //封装获取连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1.注册驱动
            Class.forName(name);
            // 2.创建链接
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //封装关闭流
    public static void release(ResultSet rs, Statement statement,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement !=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

