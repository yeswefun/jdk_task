package com.z.p2.c21;

import static com.z.util.IO.println;

class WaitSet01 {

    private static final Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + " work start");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(Thread.currentThread().getName() + " work end");
        }
    }

    /*
        线程执行到一个同步代码块的时候，
            LOCK.wait(); // 阻塞，释放锁
                调用 XXX.wait(); 之前，必须先调用 synchronized(XXX)
            阻塞在哪里?
            在哪里唤醒?
            唤醒的方式?
                LOCK.notify()
                LOCK.notifyAll()
                Thread#interrupt
                LOCK.wait(ms);

        1. 所有的对象都会有一个wait-set, 用来存放调用了该对象wait方法之后进入block状态线程
        2. 线程被notify之后，不一定立即得到执行(必须先抢到锁才能得到执行权)
        3. 线程从wait-set中被唤醒顺序不一定是FIFO
        4. 线程被唤醒后，必须重新获取锁
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    work();
                }
            }.start();
        }

        Thread.sleep(1500);

        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}
