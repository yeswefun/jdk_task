package com.z.p3.c58;

import java.util.concurrent.Exchanger;

class ExchangerTest05 {

    /*
        Exchanger 是否可以重复使用
            可以重复使用
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String ret = exchanger.exchange("A-0");
                    System.out.println(name + " : " + ret);

                    ret = exchanger.exchange("A-1");
                    System.out.println(name + " : " + ret);

                    ret = exchanger.exchange("A-2");
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
                    String ret = exchanger.exchange("B-0");
                    System.out.println(name + " : " + ret);

                    ret = exchanger.exchange("B-1");
                    System.out.println(name + " : " + ret);

                    ret = exchanger.exchange("B-2");
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== B ===").start();
    }
}
