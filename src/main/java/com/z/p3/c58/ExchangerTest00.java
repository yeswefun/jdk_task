package com.z.p3.c58;

import java.util.concurrent.Exchanger;

class ExchangerTest00 {

    /*
        两个线程交换数据
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
