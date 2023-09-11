package com.z.p3.c61;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class IPCTest01 {

    private static final ReentrantLock LOCK = new ReentrantLock(); // 非公平锁

    //对同一个资源的不同操作
    private static final Condition PRODUCE_CONDITION = LOCK.newCondition();
    private static final Condition CONSUME_CONDITION = LOCK.newCondition();

    private static final LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private static final int MAX_CAPACITY = 64;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (; ; ) {
                    produce();
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "P" + i).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (; ; ) {
                    consume();
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "C" + i).start();
        }

//        持有锁的线程才能调用下面的方法
//        for (; ; ) {
//            TimeUnit.MILLISECONDS.sleep(500);
//            System.out.println("------------------------------------");
//            System.out.println("QL(P): " + LOCK.getWaitQueueLength(PRODUCE_CONDITION));
//            System.out.println("QL(C): " + LOCK.getWaitQueueLength(CONSUME_CONDITION));
//            System.out.println("WAITER(P): " + LOCK.hasWaiters(PRODUCE_CONDITION));
//            System.out.println("WAITER(C): " + LOCK.hasWaiters(CONSUME_CONDITION));
//        }
    }

    private static void produce() {
        try {
            LOCK.lock();
            while (TIMESTAMP_POOL.size() > MAX_CAPACITY) {
                PRODUCE_CONDITION.await();
            }

            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " -> " + value);
            TIMESTAMP_POOL.addLast(value);

            CONSUME_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void consume() {
        try {
            LOCK.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_CONDITION.await();
            }

            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + " ---------------> " + value);

            PRODUCE_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
