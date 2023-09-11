package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest11 {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> testUnInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> testUnInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        //还是无法打断t2
        t2.interrupt();
        System.out.println("------------------------");
    }

    static void testUnInterruptibly() {
        String name = Thread.currentThread().getName();
        try {
            lock.lock();
            System.out.println(name + " ---> lock");
            System.out.println(name + " ---> doing something ...");
            while (true) {

            }
        } finally {
            System.out.println(name + " ---> unlock");
            lock.unlock();
        }
    }
}
