package com.z.p3.c56;

import java.util.concurrent.CountDownLatch;

class LatchTest30 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(0);
        latch.countDown();
        latch.await();
        System.out.println("main thread finish");

        //Exception in thread "main" java.lang.IllegalArgumentException: count < 0
        //CountDownLatch latch2 = new CountDownLatch(-1);
    }
}
