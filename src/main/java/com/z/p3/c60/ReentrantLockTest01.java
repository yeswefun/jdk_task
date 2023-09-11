package com.z.p3.c60;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class ReentrantLockTest01 {

    /*
        wait, notify 需要依赖 synchronized
            monitor

        ReentrantLockTest00, ReentrantLockTest01 效果等价
     */
    public static void main(String[] args) {
        IntStream.range(0, 2).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                needLockBySync();
            }
        }.start());
    }

    public static void needLockBySync() {
        String name = Thread.currentThread().getName();
        synchronized (ReentrantLockTest01.class) {
            try {
                System.out.println(name + " ---> lock");
                System.out.println(name + " ---> doing something ...");
                TimeUnit.SECONDS.sleep(2); //InterruptedException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " ---> unlock");
        }
    }
}
