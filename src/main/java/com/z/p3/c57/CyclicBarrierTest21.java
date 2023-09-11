package com.z.p3.c57;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest21 {

    /*
        waiting on condition
     */
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3);

        new Thread() {
            @Override
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " start");
                    TimeUnit.SECONDS.sleep(32);
                    System.out.println(name + " wake");
                    barrier.await();
                    System.out.println(name + " ---> finish");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(name + " wake");
                        barrier.await();
                        System.out.println(name + " ---> finish");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
