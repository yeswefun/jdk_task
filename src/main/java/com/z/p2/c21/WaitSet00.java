package com.z.p2.c21;

import java.util.stream.IntStream;

import static com.z.util.IO.println;

class WaitSet00 {

    private static final Object LOCK = new Object();

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

        1. 所有的对象都会有一个wait-set, 用来存放调用了该对象wait方法之后进入blocked状态线程
        2. 线程被notify之后，不一定立即得到执行(必须先抢到锁才能得到执行权)
        3. 线程从wait-set中被唤醒顺序不一定是FIFO
     */
    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        synchronized (LOCK) {
                            try {
                                println("++++++++++++++++++ " + Thread.currentThread().getName() + " wait");
                                LOCK.wait();
                                println(Thread.currentThread().getName() + " wake");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start());

        Thread.sleep(500);

        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    synchronized (LOCK) {
                        LOCK.notify();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
