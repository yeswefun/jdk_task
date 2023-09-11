package com.z.p2.c20;

class Singleton01 {

    private static Singleton01 instance;

    private Singleton01() {
    }

    /*
        存在线程安全问题
     */
    public static Singleton01 getInstance() {
        if (instance == null) {
            instance = new Singleton01();
        }
        //return instance; //使用静态导入时，需要辨别是当前类还是其它类的
        return Singleton01.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(Singleton01.getInstance());
        }
    }
}
