package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest04 {

    public static void main(String[] args) throws InterruptedException {

        Semaphore sem = new Semaphore(2);

        for (int i = 0; i < 3; i++) {
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

        for (; ; ) {
            System.out.println("------------------------------------");
            System.out.println("available: " + sem.availablePermits()); //空闲令牌的个数
            System.out.println("queueLength: " + sem.getQueueLength()); //阻塞队列长度
            System.out.println("hasQueuedThreads: " + sem.hasQueuedThreads());  //阻塞队列是否有线程
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
