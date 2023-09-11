package com.z.p1.c12;

import static com.z.util.IO.println;

class Tg20 {
    /*
        线程池出来之前，jdk1.5之前 - ThreadGroup

        Thread能访问ThreadGroup的哪些信息

        ThreadGroup的创建
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        println(t.toString());
        println(t.getName() + "@" + t.getPriority() + "@" + t.getThreadGroup());

        println("************************ TG-1");
        ThreadGroup tg1 = new ThreadGroup("TG-1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                ThreadGroup tg = getThreadGroup();
                println(tg.getName());
                println(tg.getParent().toString());
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t1.join(100);

        println("************************ TG-2");
        ThreadGroup tg2 = new ThreadGroup(tg1, "TG-2");
        println(tg2.getName());
        println(tg2.getParent().toString());
    }
}
