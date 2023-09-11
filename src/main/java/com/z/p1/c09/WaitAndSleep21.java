package com.z.p1.c09;


import java.util.stream.Stream;

import static com.z.util.IO.println;

class WaitAndSleep21 {

    private static final Object LOCK = new Object();

    public static void m2() {
        synchronized (LOCK) {
            try {
                println(Thread.currentThread().getName() + " wait before");
                LOCK.wait();
                println(Thread.currentThread().getName() + " wait after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        sleep 不会释放对象锁(monitor), wait 会释放对象锁(monitor)并且添加到对象锁(monitor)的等待队列中
     */
    public static void main(String[] args) throws InterruptedException {

        Stream.of("W1", "W2").forEach(n -> new Thread(n) {
            @Override
            public void run() {
//              wait 释放了 锁
                m2();
            }
        }.start());

        //IllegalMonitorStateException
        Thread.sleep(1000);
        println("main thread -> notifyAll");
        synchronized (LOCK) {
            LOCK.notifyAll();
        }
    }
}
