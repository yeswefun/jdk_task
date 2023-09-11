package com.z.p1.c06;

import static com.z.util.IO.println;

class ThreadInterrupt {

    /*
        interrupt
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    /*
                    //TODO: 为什么去掉这个代码块就能够看到 true 呢?
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    println("****************** t.isInterrupted: " + isInterrupted()); // false -> true
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
