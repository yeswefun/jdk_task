package com.z.p2.c41;

class MainTest12 {

    public static void main(String[] args) {
//        final修饰的常量会在编译期间放到常量池中，不会初始化类
        System.out.println(Target.x);   // 不会触发Target加载
//        final修饰的复杂类型，在编译期间无法计算得出，会初始化类
        System.out.println(Target.y);   // 会触发Target加载
    }
}
