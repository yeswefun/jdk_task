package com.z.p3.c56;

import java.util.concurrent.CountDownLatch;

class LatchTest32 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        Thread mainThread = Thread.currentThread();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        }.start();

        new Thread() {
            @Override
            public void run() { // 不会影响这个线程
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> finish");
            }
        }.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            //Exception in thread "main" java.lang.InterruptedException
            System.out.println("------------ catch");
            e.printStackTrace();
        }
        System.out.println("main thread finish");
    }
}
