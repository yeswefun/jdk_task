package com.z.p1.c09;


import static com.z.util.IO.println;

class WaitAndSleep {

    private static final Object LOCK = new Object();

    public static void m1() {
        try {
            println("m1 sleep before");
            Thread.sleep(2000);
            println("m1 sleep after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m2() {
        synchronized (LOCK) { // wait 依赖于 synchronized，尝试注释掉 同步代码块
            try {
                println("m2 wait before");
                LOCK.wait();
                println("m2 wait after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        sleep 不依赖于 monitor, 而 wait 依赖于 monitor
     */
    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
    }
}
