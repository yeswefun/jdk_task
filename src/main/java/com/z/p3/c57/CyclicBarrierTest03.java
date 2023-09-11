package com.z.p3.c57;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest03 {

    private static final Random r = new Random();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("main thread ---> finish");
            }
        });

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

        int index = 0;
        while (index < 16) {
            System.out.println("************************ " + index);
            System.out.println(barrier.getParties());   // 传入进去
            System.out.println(barrier.getNumberWaiting()); // 调用了 await 的数量
            // due to interruption or timeout since
            // construction or the last reset,
            // or a barrier action failed due to an exception
            System.out.println(barrier.isBroken());
            Thread.sleep(500);
            index++;
        }
    }
}
