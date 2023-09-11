package com.z.p3.c61;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ConditionTest01 {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition cond = lock.newCondition();

    private static int data = 0;

    private static volatile boolean idle = true;

    /*
        流水线 - 只有生产出来才能消费
            生产出来的数据，必须先消费完，才能再生产
            消费完数据后，必须等到有新数据，才能再消费

        问题:
            C0 ---------------> 0
            P -> 1
            C0 ---------------> 1
     */
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                produceData();
            }
        }, "P").start();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (; ; ) {
                    consumeData();
                }
            }, "C" + i).start();
        }
    }

    private static void consumeData() {
        try {
            lock.lock();
            while (!idle) {
                cond.await();
            }

            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName() + " ---------------> " + data);
            idle = false;
            cond.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void produceData() {
        try {
            lock.lock(); // == synchronized
            while (idle) {
                cond.await(); // monitor.wait()
            }

            data++;
            System.out.println(Thread.currentThread().getName() + " -> " + data);
            TimeUnit.MILLISECONDS.sleep(200);
            idle = true;
            cond.signal(); // monitor.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
