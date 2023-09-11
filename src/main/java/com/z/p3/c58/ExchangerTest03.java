package com.z.p3.c58;

import java.util.concurrent.Exchanger;

class ExchangerTest03 {

    /*
        问题: Exchanger 只适合 两个线程的交互
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("A-1");
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== A-1 ===").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("A-2");
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== A-2 ===").start();

        //第三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("B"); //blocked
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== B ===").start();
    }
}
