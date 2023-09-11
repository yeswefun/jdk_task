package com.z.p1.c09;

import static com.z.util.IO.println;

class PC2 {

    /*
        volatile
     */
//    private volatile boolean isProduced = false;
    private boolean isProduced = false;

    private int i = 0;

    private final Object LOCK = new Object();

    private void produce() {
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
     */
    public static void main(String[] args) {
        PC2 pc = new PC2();

        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
    }
}
