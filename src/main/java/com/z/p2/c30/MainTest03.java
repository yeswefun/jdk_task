package com.z.p2.c30;

import java.util.Random;

import static com.z.util.IO.println;

class MainTest03 {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    private static final Random r = new Random(System.currentTimeMillis());

    /*
        JVM start main thread

        无论怎么改变sleep的时间，结果都是(顺序可能有变)
        t2 -> t2
        t1 -> t1
        ----------------------- main thread
        main -> null
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            tl.set("t1");
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(Thread.currentThread().getName() + " -> " + tl.get());
        }, "t1");

        Thread t2 = new Thread(() -> {
            tl.set("t2");
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(Thread.currentThread().getName() + " -> " + tl.get());
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("----------------------- main thread");
        println(Thread.currentThread().getName() + " -> " + tl.get());
    }
}
