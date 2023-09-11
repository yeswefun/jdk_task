package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest10 {

    public static void main(String[] args) throws InterruptedException {

        Semaphore sem = new Semaphore(3);

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    sem.drainPermits(); //获取所有可用的
                    System.out.println(name + " ---> lock");
                    System.out.println(name + " ---> doing something ...");
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(name + " ---> unlock");
                    sem.release(3);
                }
            }
        };
        t1.start();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(sem.availablePermits());

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    sem.acquire();
                    System.out.println(name + " ---> lock");
                    System.out.println(name + " ---> doing something ...");
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(name + " ---> unlock");
                    sem.release();
                }
            }
        };
        t2.start();
    }
}
