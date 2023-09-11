package com.z.p3.c58;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ExchangerTest02 {

    /*
        timeout
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("A", 1, TimeUnit.SECONDS);
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException | TimeoutException e) {
                    System.err.println("********************* catch");
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
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(name + " ***> wake");
                    String ret = exchanger.exchange("B"); // blocked
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== B ===").start();
    }
}
