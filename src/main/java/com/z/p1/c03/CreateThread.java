package com.z.p1.c03;

import static com.z.util.IO.println;

class CreateThread {
    /*
        Thread的构造方法
        JVM 创建 main 线程
     */
    public static void main(String[] args) {
        //Thread-0
        Thread t0 = new Thread();

        //Thread-1
        Thread t1 = new Thread() {
            @Override
            public void run() {
                println(Thread.currentThread().getName() + "#run");
            }
        };
        t0.start();
        t1.start();
        println("Thread -> " + t0.getName());
        println("Thread -> " + t1.getName());

        //t2
        Thread t2 = new Thread("t2");
        println("Thread -> " + t2.getName());

        //Thread-2
        Thread t3 = new Thread();
        println("Thread -> " + t3.getName());

        //t4
        Thread t4 = new Thread(() -> {
            println(Thread.currentThread().getName() + "#run");
        }, "t4");
        t4.start();
    }
}
