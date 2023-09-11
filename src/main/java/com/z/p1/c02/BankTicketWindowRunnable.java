package com.z.p1.c02;

import static com.z.util.IO.println;

class BankTicketWindowRunnable implements Runnable {

    private static final int MAX = 30;

    //重复打印相同号码
    private static int index = 1;

    @Override
    public void run() {
        while (index <= MAX) {
            println(Thread.currentThread().getName() + " -> num: " + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
