package com.z.p1.c03;

import java.util.Arrays;

import static com.z.util.IO.println;

class CreateThread2 {

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        println("t.getThreadGroup(): " + t.getThreadGroup());
        println("currentThread(): " + Thread.currentThread().getName());

        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        println("currentThread.getThreadGroup(): " + tg.getName());
        int activeCount = tg.activeCount();
        println("activeCount: " + activeCount);

        Thread[] threads = new Thread[activeCount];
        int ret = tg.enumerate(threads);
        println("ret: " + ret);

        println("=================================");
        for (Thread thread : threads) {
            println("Thread: " + thread.getName());
        }

        println("================================= java8");
        Arrays.asList(threads).forEach(System.out::println);
    }
}
