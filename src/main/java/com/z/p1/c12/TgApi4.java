package com.z.p1.c12;

import static com.z.util.IO.println;

class TgApi4 {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup tg = new ThreadGroup("TG-1");
        Thread t1 = new Thread(tg, "t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //A daemon thread group is automatically destroyed
        //when its last thread is stopped or its last thread group is destroyed.
        tg.setDaemon(true);
        t1.start();

        System.out.println("isDaemon: " + tg.isDaemon());

        println("isDestroyed: " + tg.isDestroyed());
        Thread.sleep(1500);
        println("isDestroyed: " + tg.isDestroyed());
    }
}
