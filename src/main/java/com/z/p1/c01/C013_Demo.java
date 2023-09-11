package com.z.p1.c01;

import static com.z.util.IO.println;

class C013_Demo {

    /*
        同一个Thread对象不能调用start两次

        public class Thread implements Runnable {}

        Thread#start

        模板方法
            存在固定逻辑
     */
    public static void main(String[] args) {

        println("thread: " + Thread.currentThread().getName());

        Thread t = new Thread("test") {
            @Override
            public void run() {
                println("thread-x: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start(); //创建线程
//        t.start(); //创建线程, java.lang.IllegalThreadStateException

        t.run(); // 没有创建新的线程
    }
}
