package com.z.p1.c12;

import static com.z.util.IO.println;

class TgApi5 {

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
//        tg.setDaemon(true);
        t1.start();

        System.out.println("isDaemon: " + tg.isDaemon());

        println("isDestroyed: " + tg.isDestroyed());
        Thread.sleep(1500);
        println("isDestroyed: " + tg.isDestroyed());
        /*
            Destroys this thread group and all of its subgroups.
            This thread group must be empty, indicating that
            all threads that had been in this thread group
            have since stopped.
         */
        tg.destroy();
        println("isDestroyed: " + tg.isDestroyed());
    }
}
