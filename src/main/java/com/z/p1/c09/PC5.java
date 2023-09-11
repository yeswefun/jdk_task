package com.z.p1.c09;

import java.util.stream.Stream;

import static com.z.util.IO.println;

class PC5 {

    /*
        volatile
     */
//    private volatile boolean isProduced = false;
    private boolean isProduced = false;

    private int i = 0;

    private final Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            while (isProduced) { // 不是if, 此处用的是 notifyAll
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            println(Thread.currentThread().getName() + " -> " + (++i));
            isProduced = true;
            LOCK.notifyAll();
        }
    }

    /*
        重复消费现象 - fix
            C1 -> wait
            C2 -> wait
            P1 -> 1
                notifyAll

            C1 and C2 wake at the same time - fix
            还有可能存在重复生产的问题 - fix
     */
    private void consume() {
        synchronized (LOCK) {
            while (!isProduced) { // 不是if, 此处用的是 notifyAll
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //三个线程同时从等待队列中醒了过来，
            //只有一个抢到 LOCK, 其它两个线程会阻塞在 LOCK 上 (继续wait)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO: 这句代码的顺序在此处到底重不重要?
            //isProduced = false;
            //TODO: 会不会出现在打印完之后就失去cpu的控制权，没有来得及修改 isProduced 而导致 上面的 while(!isProduced)失去它的作用?
            println(Thread.currentThread().getName() + " ------------> " + i);
            isProduced = false;
            LOCK.notifyAll();
        }
    }

    /*
        出现的问题 - fix
            有的没有消费
            有的重复消费

        解决: 传输带
            生产完了通知消费
            消费完了通知生产

        问题: 多生产者与多消费者
            jps
            jstack PID -> 没有出现死锁现象

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

        PC5 pc = new PC5();

        Stream.of("P1", "P2", "P3").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());

        Stream.of("C1", "C2", "C3", "C4").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());
    }
}
