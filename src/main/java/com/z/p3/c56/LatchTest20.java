package com.z.p3.c56;

import java.util.concurrent.CountDownLatch;

class LatchTest20 {

    /*
        多个线程轮流处理同一个任务
     */
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        //两个线程需要在另一个线程的执行完之后才能执行
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " ---> start");
                    try {
                        Thread.sleep(1000);
                        latch.await();
                        System.out.println(name + " do other work");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    for (int i = 0; i < 8; i++) {
                        System.out.println(name + " prepare -> " + i);
                        Thread.sleep(200);
                    }
                    System.out.println(name + " finish preparation");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }.start();
    }
}
