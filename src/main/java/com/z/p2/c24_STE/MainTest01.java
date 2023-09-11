package com.z.p2.c24_STE;

class MainTest01 {

    /*
        Single ThreadedExecution design pattern

        多个线程
            共享资源是什么
            临界区是什么
            竞争什么

        共享资源, shareResource
        临界区
        时序竞态, race
     */
    public static void main(String[] args) {

        Gate2 gate = new Gate2();

        User2 u1 = new User2("B", "BeiJing", gate);
        User2 u2 = new User2("S", "ShangHai", gate);
        User2 u3 = new User2("G", "GuangZhou", gate);

        u1.start();
        u2.start();
        u3.start();
    }
}
