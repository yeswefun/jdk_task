package com.z.p1.c08;

class BankRunnableFix2y {

    /*
        每个 TicketWindow 都有一个单独的 index - fix

        将可执行单元与线程控制逻辑分离
        所有 TicketWindow 共用同一个 index
            出现重复的 index - fix
     */
    public static void main(String[] args) {

        TicketWindowRunnableFix2y r = new TicketWindowRunnableFix2y();
        Thread t1 = new Thread(r, "win-1");
        Thread t2 = new Thread(r, "win-2");
        Thread t3 = new Thread(r, "win-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
