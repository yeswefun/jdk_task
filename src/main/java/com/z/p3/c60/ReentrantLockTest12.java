package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest12 {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> testInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> testInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        //打断t2
        t2.interrupt();
        System.out.println("------------------------");
    }

    static void testInterruptibly() {
        String name = Thread.currentThread().getName();
        try {
            lock.lockInterruptibly();
            System.out.println(name + " ---> lock");
            System.out.println(name + " ---> doing something ...");
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " ---> unlock");
            lock.unlock();
        }
    }
}
