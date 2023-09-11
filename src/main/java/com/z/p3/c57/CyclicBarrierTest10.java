package com.z.p3.c57;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest10 {

    /*
        java.util.concurrent.BrokenBarrierException
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(2);

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("****** barrier#await");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    //java.util.concurrent.BrokenBarrierException
                    System.out.println("******************* catch");
                    e.printStackTrace();
                }
            }
        }.start();

        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("********* before");
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.isBroken());
        barrier.reset();
        System.out.println("********* after");
        System.out.println(barrier.getParties());
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.isBroken());
    }
}
