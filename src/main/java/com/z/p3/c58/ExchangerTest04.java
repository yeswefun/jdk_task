package com.z.p3.c58;

import java.util.concurrent.Exchanger;

class ExchangerTest04 {

    /*
        线程A中的参数 与 线程B中的返回值 是否是 同一份
            是同一份
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " ---> start");
                try {
                    String param = "A";
                    System.out.println(param.hashCode());
                    String ret = exchanger.exchange(param);
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
                    System.out.println(ret.hashCode());
                    System.out.println(name + " : " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " ---> end");
            }
        }, "=== B ===").start();
    }
}
