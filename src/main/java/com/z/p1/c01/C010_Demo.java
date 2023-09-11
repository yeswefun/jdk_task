package com.z.p1.c01;

import static com.z.util.IO.println;

class C010_Demo {

    /*
        顺序执行
     */
    public static void main(String[] args) throws InterruptedException {

        /*
            demo-3
                1. 运行main方法
                2. cmd, jps 查看 pid
                3. jconsole pid
                    不安全连接 / 线程 / main
         */
//        Thread.sleep(1000 * 300L);

        //demo-1
        test();

        //demo-2
        readDataFromDB();
        writeDataToFile();
    }

    private static void test() {
        for (int i = 0; i < 8; i++) {
            println("Task-1 --> " + i);
        }
        for (int i = 0; i < 8; i++) {
            println("Task-2 ------------------------> " + i);
        }
    }

    private static void readDataFromDB() {
        try {
            println("readFromDB ************ before");
            Thread.sleep(1000 * 2);
            println("readFromDB ************ after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("readFromDB ************ success");
    }

    private static void writeDataToFile() {
        try {
            println("writeDataToFile ######################## before");
            Thread.sleep(1000 * 2);
            println("writeDataToFile ######################## after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("writeDataToFile ######################## success");
    }
}
