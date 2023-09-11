package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

class ReentrantLockTest00 {

    private static Lock lock = new ReentrantLock();

    /*
        wait, notify 需要依赖 synchronized
            monitor
     */
    public static void main(String[] args) {
        IntStream.range(0, 2).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                needLock();
            }
        }.start());
    }

    public static void needLock() {
        String name = Thread.currentThread().getName();
        try {
            lock.lock();
            System.out.println(name + " ---> lock");
            System.out.println(name + " ---> doing something ...");
            TimeUnit.SECONDS.sleep(2); //InterruptedException
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " ---> unlock");
            lock.unlock();
        }
    }
}
