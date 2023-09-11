package com.z.p3.c60;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class RWLockTest00 {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final List<Long> data = new ArrayList<>();

    /*
        W W, X
        W R, X
        R W, X
        R R, O

        读共享，写独占，写优先级高
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> write());
        t0.start();

        TimeUnit.MILLISECONDS.sleep(200);
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> read());
            t.start();
        }
    }

    private static void write() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(2);
            data.add(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void read() {
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " ---> read");
            data.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
