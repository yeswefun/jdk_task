package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest06 {

    /*
        不可中断
     */
    public static void main(String[] args) throws InterruptedException {

        Semaphore sem = new Semaphore(1);

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    sem.acquire();
                    System.out.println(name + " ---> lock");
                    System.out.println(name + " ---> doing something ...");
                    TimeUnit.MILLISECONDS.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(name + " ---> unlock");
                    sem.release();
                }
            }
        };
        t1.start();

        TimeUnit.MILLISECONDS.sleep(200);

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    sem.acquireUninterruptibly();
                    System.out.println(name + " ---> lock");
                    System.out.println(name + " ---> doing something ...");
                } finally {
                    System.out.println(name + " ---> unlock");
                    sem.release();
                }
            }
        };
        t2.start();

        TimeUnit.MILLISECONDS.sleep(200);
        t2.interrupt();
    }
}
