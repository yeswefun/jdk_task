package com.z.p1.c06;

import static com.z.util.IO.println;

class ThreadInterrupt2 {

    /*
        interrupt
            sleep
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    println("t -> index: " + (index++));
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        println("****************** t.isInterrupted: " + isInterrupted()); // false
                        break;
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
