package com.z.p1.c09;


import java.util.stream.Stream;

import static com.z.util.IO.println;

class WaitAndSleep20 {

    private static final Object LOCK = new Object();

    public static void m1() {
        synchronized (LOCK) {
            try {
                println(Thread.currentThread().getName() + " sleep before");
                Thread.sleep(3000);
                println(Thread.currentThread().getName() + " sleep after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        sleep 不会释放对象锁(monitor), wait 会释放对象锁(monitor)并且添加到对象锁(monitor)的等待队列中
     */
    public static void main(String[] args) throws InterruptedException {

        Stream.of("S1", "S2").forEach(n -> new Thread(n) {
            @Override
            public void run() {
//              sleep 不会释放 锁, 串行执行
                m1();
            }
        }.start());
    }
}
