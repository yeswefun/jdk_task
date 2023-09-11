package com.z.p1.c09;

import java.util.stream.Stream;

import static com.z.util.IO.println;

class PC3 {

    /*
        volatile
     */
//    private volatile boolean isProduced = false;
    private boolean isProduced = false;

    private int i = 0;

    private final Object LOCK = new Object();

    private void produce() {
        println(Thread.currentThread().getName());
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                println(Thread.currentThread().getName() + " -> " + (++i));
                isProduced = true;
                LOCK.notify();
            }
        }
    }

    private void consume() {
        println(Thread.currentThread().getName());
        synchronized (LOCK) {
            if (isProduced) {
                println(Thread.currentThread().getName() + " ------------> " + i);
                isProduced = false;
                LOCK.notify();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
        出现的问题 - 主要原因是 不同步 - fix
            有的没有消费
            有的重复消费

        解决: 传输带
            生产完了通知消费
            消费完了通知生产

        问题: 多生产者与多消费者
            jps
            jstack PID -> 没有出现死锁现象

        两个消费先阻塞，然后一个生产者唤醒了另一个生产者

        notify 是随机唤醒一个
            P2
            P1, failed to get the lock, and get stuck on the lock
            P2 -> 1, isProduced == true
            P2, wait
            C1
            C2, failed to get the lock, and get stuck on the lock
            C1 ------------> 1, isProduced == false
            C1, wait
            P1,
            P1 -> 2, isProduced == true
            P1, wait
            P2

        注: wait会释放 monitor
     */
    public static void main(String[] args) {

        PC3 pc = new PC3();

        Stream.of("P1", "P2").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start());

        Stream.of("C1", "C2").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start());
    }
}
