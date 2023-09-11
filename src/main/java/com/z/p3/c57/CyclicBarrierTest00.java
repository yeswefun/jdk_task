package com.z.p3.c57;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest00 {

    private static final Random r = new Random();

    public static void main(String[] args) {

        int n = 3;
        CyclicBarrier barrier = new CyclicBarrier(n);

        for (int i = 0; i < n; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " ------> start");
                        TimeUnit.MILLISECONDS.sleep(1000 + r.nextInt(3000));
                        System.out.println(name + " wake");
                        barrier.await();
                        System.out.println(name + " ------> finish");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
