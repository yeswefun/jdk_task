package com.z.p3.c57;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest11 {

    private static CyclicBarrier barrier;

    /*
        java.util.concurrent.BrokenBarrierException
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        barrier = new CyclicBarrier(2);

        tip("init");

        //后调用 await
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //先调用 await
        new Thread() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        TimeUnit.MILLISECONDS.sleep(200);
        tip("before");
        TimeUnit.MILLISECONDS.sleep(2000);
        tip("sleep");
        barrier.reset();
        tip("after");
    }

    private static void tip(String tag) {
        System.out.println("********* " + tag);
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.isBroken());
    }
}
