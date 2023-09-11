package com.z.p1.c08;

import static com.z.util.IO.println;

class TicketWindowRunnableFix implements Runnable {

    private static final int MAX = 30;

    private static int index = 1;

    private final Object MONITOR = new Object();

    /*
        线程安全
        在 1 和 2 之间 是单线程
     */
    @Override
    public void run() {
        while (true) {
            //1
            synchronized (MONITOR) {
                if (index > MAX) {
                    println(Thread.currentThread().getName() + " -> no tickets");
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                println(Thread.currentThread().getName() + " -> num: " + (index++));
            }
            //2

            /*
            问题: 只有一个窗口在工作
            将下面代码注释掉，会发现只有一个窗口在工作，
            不知道是不是因为 while (true) 太快了*/
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
