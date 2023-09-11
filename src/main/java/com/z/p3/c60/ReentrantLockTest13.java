package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest13 {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> tryLock());
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> tryLock());
        t2.start();
    }

    static void tryLock() {
        String name = Thread.currentThread().getName();
        if (lock.tryLock()) {
            try {
                System.out.println(name + " ---> lock");
                System.out.println(name + " ---> doing something ...");
                while (true) {
                }
            } finally {
                System.out.println(name + " ---> unlock");
                lock.unlock();
            }
        } else {
            System.out.println(name + " ---> failed to lock");
        }
    }
}
