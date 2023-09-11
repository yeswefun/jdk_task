package com.z.p1.c10;

import static com.z.util.IO.println;

class Demo01SynchronizedIssue {

    /*
        Synchronized 的不足
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread("t0") {
            @Override
            public void run() {
                Demo01SynchronizedIssue.run();
            }
        }.start();

        println("************ main Thread sleep before");
        Thread.sleep(300);
        println("************ main Thread sleep after");

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                Demo01SynchronizedIssue.run(); //阻塞在锁上，并且无法被打断
            }
        };
        t1.start();

        //t1因为争抢锁失败而被阻塞，而且无法被打断
        Thread.sleep(100);
        t1.interrupt();
        println("state: " + t1.getState());
    }

    private static synchronized void run() {

        println(Thread.currentThread().getName() + " ---> run");

        while (true) {

        }
    }
}
