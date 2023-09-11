package com.z.p3.c60;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class RWLockTest02 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();

    private static final List<Long> data = new ArrayList<>();

    /*
        W W, X
        W R, X
        R W, X
        R R, O

        读共享，写独占，写优先级高
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> write());
            t.start();
        }
    }

    private static void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " ---> write");
            TimeUnit.SECONDS.sleep(2);
            data.add(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    private static void read() {
        try {
            readLock.lock();
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " ---> read");
            data.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
