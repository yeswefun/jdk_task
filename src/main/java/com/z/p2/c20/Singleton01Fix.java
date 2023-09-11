package com.z.p2.c20;

class Singleton01Fix {

    private static Singleton01Fix instance;

    private Singleton01Fix() {
    }

    /*
        每次读操作，都需要进行加锁
            锁的力度大
     */
    public synchronized static Singleton01Fix getInstance() {
        if (instance == null) {
            instance = new Singleton01Fix();
        }
        return Singleton01Fix.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(Singleton01Fix.getInstance());
        }
    }
}
