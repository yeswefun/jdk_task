package com.z.p3.c57;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest01 {

    private static final Random r = new Random();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3);

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " sleep");
                        TimeUnit.MILLISECONDS.sleep(200 + r.nextInt(3000));
                        System.out.println(name + " await");
                        barrier.await();
                        System.out.println(name + " ---> finish");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        System.out.println("main thread await");
        barrier.await();
        System.out.println("main thread ---> finish");
    }
}
