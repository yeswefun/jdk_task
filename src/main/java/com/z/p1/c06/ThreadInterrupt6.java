package com.z.p1.c06;

import static com.z.util.IO.println;

class ThreadInterrupt6 {

    /*
        interrupt
     */
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            int index = 0;
            while (true) {
                println(Thread.currentThread().getName() + " -> index: " + (index++));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        Thread main = Thread.currentThread();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                println("---> invoke main.interrupt()");
                //谁在阻塞，就打断谁
                //t.interrupt();
                main.interrupt();
            }
        }.start();

        //打断join
        try {
            t.join();//是主线程停了下来，而不是t线程停了下来
        } catch (InterruptedException e) {
            e.printStackTrace();
            println("***> main thread was interrupted");
        }
        //-------------------------- block until t finish
        println("---> main thread finish");
    }
}
