package com.z.p2.c20;

class Singleton00 {

    /*
         不能延迟分配内存
     */
    private static final Singleton00 instance = new Singleton00();

    // forbid new Singleton00 outside this class
    private Singleton00() {
    }

    public static Singleton00 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(getInstance());
        }
    }
}
