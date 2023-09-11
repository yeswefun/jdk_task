package com.z.p2.c41;

class MainTest03 {
    /*
        Class.forName, 反射某个类
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.z.p2.c41.Target");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
