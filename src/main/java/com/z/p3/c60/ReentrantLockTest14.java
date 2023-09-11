package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest14 {

    private static ReentrantLock lock = new ReentrantLock();

    /*
        ReentrantLock
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> testUnInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> testUnInterruptibly());
        t2.start();

        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println("---------------------------");
        System.out.println(lock.getQueueLength());      //1
        System.out.println(lock.hasQueuedThreads());    //true
        System.out.println(lock.hasQueuedThread(t1));   //false
        System.out.println(lock.hasQueuedThread(t2));   //true
        System.out.println(lock.isLocked());            //true
    }

    static void testUnInterruptibly() {
        String name = Thread.currentThread().getName();
        try {
            lock.lock();
            System.out.println(name + " ---> lock");
            System.out.println(name + " ---> hold lock: " + lock.getHoldCount());
            System.out.println(name + " ---> doing something ...");
            while (true) {

            }
        } finally {
            System.out.println(name + " ---> unlock");
            lock.unlock();
        }
    }
}
