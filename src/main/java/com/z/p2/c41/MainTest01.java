package com.z.p2.c41;

class MainTest01 {

    /*
        访问某个类或者接口的静态变量
     */
    public static void main(String[] args) {
//        Target.num = 6; // 触发类加载
        System.out.println(Target.num); // 触发类加载
    }
}
