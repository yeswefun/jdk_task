package com.z.p2.c49;

import java.sql.Connection;
import java.sql.DriverManager;

class MainTest01 {

    /*
        类加载器的命名空间
        jdbc数据库厂商如何打破父委托机制?
     */
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        //public static Connection getConnection(
        //  String url,
        //  java.util.Properties info) throws SQLException {}
        Connection conn = DriverManager.getConnection("");
    }
}
