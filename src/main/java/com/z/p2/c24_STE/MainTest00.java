package com.z.p2.c24_STE;

class MainTest00 {

    /*
        对共享资源加锁
     */
    public static void main(String[] args) {

        Gate gate = new Gate();

        User u1 = new User("B", "BeiJing", gate);
        User u2 = new User("S", "ShangHai", gate);
        User u3 = new User("G", "GuangZhou", gate);

        u1.start();
        u2.start();
        u3.start();
    }
}
