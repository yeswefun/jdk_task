package com.z.p3.c61;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ConditionTest10 {

    private static final ReentrantLock lock = new ReentrantLock(true); // 公平锁

    private static final Condition cond = lock.newCondition();

    private static int data = 0;

    private static volatile boolean idle = true;

    /*
        不使用 Condition 而只使用 Lock?

        不使用 Lock 而只使用 Condition?
     */
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                produceData();
            }
        }, "P").start();

        new Thread(() -> {
            for (; ; ) {
                consumeData();
            }
        }, "C").start();

//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                for (; ; ) {
//                    consumeData();
//                }
//            }, "C" + i).start();
//        }
    }

    private static void consumeData() {
        try {
            lock.lock();
//            while (!idle) {
//                cond.await();
//            }

            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName() + " ---------------> " + data);
//            idle = false;
//            cond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void produceData() {
        try {
            lock.lock(); // == synchronized
//            while (idle) {
//                cond.await(); // monitor.wait()
//            }

            data++;
            System.out.println(Thread.currentThread().getName() + " -> " + data);
            TimeUnit.MILLISECONDS.sleep(200);
//            idle = true;
//            cond.signalAll(); // monitor.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
