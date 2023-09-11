package com.z.p1.c08;

import static com.z.util.IO.println;

class TicketWindowRunnableFix2y implements Runnable {

    //只读共享数据
    private static final int MAX = 30;

    private static int index = 1;

    /*
        只能由一个窗口全部消费 - fix
            同步方法使用的锁是 this
     */
    @Override
    public void run() {
        println(Thread.currentThread().getName() + "#run");
        while (!ticket()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        同步代码，尽量力度小
     */
    private boolean ticket() {
        synchronized (this) {
            //读取共享数据
            if (index > MAX) {
                println(Thread.currentThread().getName() + " -> no tickets");
                return true;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //index++
            //get Field
            //index = index + 1
            //put Field
            println(Thread.currentThread().getName() + " -> num: " + (index++));
            return false;
        }
    }
}
