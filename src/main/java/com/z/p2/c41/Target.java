package com.z.p2.c41;

import java.util.Random;

class Target {

    static {
        System.out.println("****************** Target");
    }

    public static int num = 8;

    public static final int x = 16; // 常量池，编译时常量
    public static final int y = new Random().nextInt(32); // 运行时常量，必须加载了才会有值

    public static void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        System.out.println(y);
    }
}
