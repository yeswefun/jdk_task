package com.z.p1.c12;

import static com.z.util.IO.println;

class Tg21 {
    /*
        线程池出来之前，jdk1.5 - ThreadGroup
            管理一个组的线程

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
                println(tg.getParent().activeCount() + ""); //只读信息
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t1.join(100);

        println("************************ TG-2");
        ThreadGroup tg2 = new ThreadGroup("TG-2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                //在tg2中的线程访问tg1的信息
                println("t2#run -> " + tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                for (Thread thread : threads) {
                    println("t2#run.loop -> " + thread.getName());
                }
                //Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t2.start();
        println(tg2.getName());
        println(tg2.getParent().toString());
    }
}
