package com.z.p1.c12;

import static com.z.util.IO.println;

class TgApi {

    /*
        main
            tg1 -> t1
                tg2 -> t2

        estimate
            取出来时，线程组中的线程可能会结束掉，也有可能有新的线程加入到线程组中
     */
    public static void main(String[] args) {

        ThreadGroup tg1 = new ThreadGroup("TG-1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG-2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();

        println("************************");
        println("" + tg1.activeCount()); // 2
        println("" + tg1.activeGroupCount()); // 1

        /*
            当前线程是否有权限去修改:
                是否是守护线程
         */
//        t2.checkAccess();

        /*
            IllegalThreadStateException
                有条件的
         */
//        tg1.destroy();

        /*
            tg1线程组内所有线程及tg1子线程组中的所有线程
         */
//        tg1.interrupt();
    }
}
