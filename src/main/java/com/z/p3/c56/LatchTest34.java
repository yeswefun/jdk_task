package com.z.p3.c56;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class LatchTest34 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> finish");
                latch.countDown();
            }
        }.start();

        latch.await(1500, TimeUnit.MILLISECONDS);
        System.out.println("main thread finish");
    }
}
