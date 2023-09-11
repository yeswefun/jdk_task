package com.z.p1.c03;

import static com.z.util.IO.println;

class CreateThread3 {

    /*
    private int i = 0;

    private byte[] bytes = new byte[1024];

    i 与 bytes 都是放在方法区
            bytes的内容在堆区
        线程共享方法区，堆区内容
     */
    private static int counter = 0;

    /*
        JVM创建一个线程执行main方法
            创建一个虚拟机栈，线程私有

        在主线程中
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Error e) {
            e.printStackTrace();
            println("counter: " + counter); //counter: 20636
        }
    }

    /*
        java.lang.StackOverflowError
     */
    private static void test() {
        counter++;
        test();
    }
}
