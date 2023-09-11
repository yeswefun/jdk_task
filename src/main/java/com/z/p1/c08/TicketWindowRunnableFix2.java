package com.z.p1.c08;

import static com.z.util.IO.println;

class TicketWindowRunnableFix2 implements Runnable {

    private static final int MAX = 30;

    private static int index = 1;

    /*
        只能由一个窗口全部消费
            同步方法使用的锁是 this
     */
    @Override
    public synchronized void run() { // 同一个Runnable实例，同一把锁
        while (true) {
            if (index > MAX) {
                println(Thread.currentThread().getName() + " -> no tickets");
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(Thread.currentThread().getName() + " -> num: " + (index++));
        }
    }
}
