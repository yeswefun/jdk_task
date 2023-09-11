package com.z.p1.c06;

import static com.z.util.IO.println;

class ThreadInterrupt3 {

    private static final Object LOCK = new Object();

    /*
        interrupt
            wait
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (LOCK) {
                        try {
                            LOCK.wait(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            println("****************** t.isInterrupted: " + isInterrupted()); // false
                            break;
                        }
                    }
                }
            }
        };
        t.start();

        Thread.sleep(1000);
        println("t isInterrupted: " + t.isInterrupted()); // false
        t.interrupt();
        println("t isInterrupted: " + t.isInterrupted()); // true
    }
}
