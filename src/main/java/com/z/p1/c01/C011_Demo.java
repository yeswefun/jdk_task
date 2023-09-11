package com.z.p1.c01;

import static com.z.util.IO.println;

class C011_Demo {

    /*
        交替执行
     */
    public static void main(String[] args) {

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                for (int i = 0; i < 8; i++) {
                    println("Task-1 --> " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start(); //创建线程

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                for (int i = 0; i < 8; i++) {
                    println("Task-2 ------------------------> " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start(); //创建线程
    }
}
