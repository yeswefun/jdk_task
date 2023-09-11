package com.z.p2.c20;

class Singleton01Fix2 {

    private static Singleton01Fix2 instance;

    private Singleton01Fix2() {
    }

    /*
        每次读操作，都需要进行加锁
            锁的力度大

        分析三个线程同时调用 getInstance 的情景

        DCL: Double Check Lock
     */
    public static Singleton01Fix2 getInstance() {
        if (instance == null) {
            synchronized (Singleton01Fix2.class) {
                if (instance == null) {
                    instance = new Singleton01Fix2();
                }
            }
        }
        return Singleton01Fix2.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(Singleton01Fix2.getInstance());
        }
    }
}
