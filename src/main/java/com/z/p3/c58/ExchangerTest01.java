package com.z.p3.c58;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

class ExchangerTest01 {

    /*
        必须两个线程都调用 exchange 才能获取对方各自的返回值，
        否则 先调用 exchange 的一方会被阻塞
            linux - 管道, pipe
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("A");
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== A ===").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    System.out.println(name + " ***> sleep");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(name + " ***> wake");
                    String ret = exchanger.exchange("B");
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== B ===").start();
    }
}
