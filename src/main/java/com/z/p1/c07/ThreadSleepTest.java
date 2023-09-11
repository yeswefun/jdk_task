package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadSleepTest {

    /*
        ThreadService
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    println(Thread.currentThread().getName() + " -> index: " + (index++));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        //阻塞的是主线程
        t.sleep(10_000);
        println("main thread exit");
    }
}
