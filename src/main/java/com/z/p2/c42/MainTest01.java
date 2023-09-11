package com.z.p2.c42;

class MainTest01 {

    private static int x = 10;

    static {
        System.out.println(x);
        x++;
        System.out.println(x);
//        Illegal forward reference, 静态代码块内不能访问其后的静态变量
//        System.out.println(y);
//        y++;
//        System.out.println(y);
    }

    private static int y = 20;

    public static void main(String[] args) {
        System.out.println("********************* main");
        System.out.println(x);
        System.out.println(y);
    }
}
