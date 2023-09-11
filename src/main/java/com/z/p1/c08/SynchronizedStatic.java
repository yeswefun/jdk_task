package com.z.p1.c08;

import static com.z.util.IO.println;

class SynchronizedStatic {

    /*
        静态代码块，m1， m2 争抢的是同一把锁
     */
    static {
        new Thread(() -> {
            synchronized (SynchronizedStatic.class) {
                try {
                    println("static, " + Thread.currentThread().getName() + " ---> lock");
                    Thread.sleep(3_000);
                    println("static, " + Thread.currentThread().getName() + " ---> unlock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private synchronized static void m1() {
        println("m1, " + Thread.currentThread().getName());
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void m2() {
        println("m2, " + Thread.currentThread().getName());
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void m3() {
        println("m3, " + Thread.currentThread().getName());
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread("T1") {
            @Override
            public void run() {
                m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                m2();
            }
        }.start();

        new Thread("T3") {
            @Override
            public void run() {
                m3();
            }
        }.start();
    }
}
