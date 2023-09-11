package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest01 {

    /*
        锁不够用时，会阻塞请求锁的线程
     */
    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1); // 1 | 2

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        sem.acquire();
                        System.out.println(name + " ---> lock");
                        System.out.println(name + " ---------> doing something ...");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        sem.release();
                        System.out.println(name + " ---> unlock");
                    }
                }
            }.start();
        }

    }
}
