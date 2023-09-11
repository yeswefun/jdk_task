package com.z.p1.c06;

import static com.z.util.IO.println;

class ThreadInterrupt5 {

    private static final Object LOCK = new Object();

    /*
        interrupt
            wait
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    try {
                        LOCK.wait(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        println("****************** t.isInterrupted: " + Thread.interrupted()); // false
                        //对象的 isInterrupted 方法，不能使用
                        //println("****************** t.isInterrupted: " + this.isInterrupted());
                        break;
                    }
                }
            }
        });
        t.start();

        Thread.sleep(1000);
        println("t isInterrupted: " + t.isInterrupted()); // false
        t.interrupt();
        println("t isInterrupted: " + t.isInterrupted()); // true
    }
}
