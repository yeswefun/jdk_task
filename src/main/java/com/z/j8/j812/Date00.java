package com.z.j8.j812;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Date00 {

    public static void main(String[] args) {
//        test00();
//        test01();
        test02();
    }

    //线程不安全
    private static void test02() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 32; i++) {
            new Thread(() -> {
                try {
                    Date parseDate = sdf.parse("20160505");
                    System.out.println(parseDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    //Thu May 05 00:00:00 CST 2016
    private static void test01() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parseDate = sdf.parse("20160505");
            System.out.println(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Fri Mar 18 00:00:00 CST 2016
    private static void test00() {
        Date date = new Date(116, 2, 18); // 1900
        System.out.println(date);
    }
}
