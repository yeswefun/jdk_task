package com.z.p1.c08;

import static com.z.util.IO.println;

class TicketWindowRunnable implements Runnable {

    private static final int MAX = 30;

    //所有实例共有一份
    private static int index = 1;

    /*
        存在线程安全问题
            出现相同的数
            出现大于30的数
     */
    @Override
    public void run() {
        while (true) {
            if (index > MAX)
                break;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(Thread.currentThread().getName() + " -> num: " + (index++));
        }
    }
}
