package com.z.p1.c09;

import static com.z.util.IO.println;

class PC {

    private int i = 0;

    private final Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + " -> " + (++i));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + " ------------> " + i);
        }
    }

    /*
        出现的问题 - 主要原因是 不同步
            有的没有消费
            有的重复消费
     */
    public static void main(String[] args) {
        PC pc = new PC();

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
