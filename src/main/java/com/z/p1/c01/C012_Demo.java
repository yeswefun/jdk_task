package com.z.p1.c01;

import static com.z.util.IO.println;

class C012_Demo {

    /*
        交替执行
     */
    public static void main(String[] args) {

        println("thread: " + Thread.currentThread().getName());

        new Thread("readDataFromDB") {
            @Override
            public void run() {
                println("thread: " + Thread.currentThread().getName());
                for (int i = 0; i < 8; i++) {
                    readDataFromDB();
                }
            }
        }.start(); //创建线程

        new Thread("writeDataToFile") {
            @Override
            public void run() {
                println("thread: " + Thread.currentThread().getName());
                for (int i = 0; i < 8; i++) {
                    writeDataToFile();
                }
            }
        }.start(); //创建线程
    }

    private static void readDataFromDB() {
        try {
            println("readFromDB ************ before");
            Thread.sleep(500);
            println("readFromDB ************ after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("readFromDB ************ success");
    }

    private static void writeDataToFile() {
        try {
            println("writeDataToFile ######################## before");
            Thread.sleep(500);
            println("writeDataToFile ######################## after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("writeDataToFile ######################## success");
    }
}
