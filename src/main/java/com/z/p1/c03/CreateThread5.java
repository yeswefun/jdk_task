package com.z.p1.c03;

import static com.z.util.IO.println;

class CreateThread5 {

    private static int counter = 0;

    /*
        JVM创建一个线程执行main方法
            创建一个虚拟机栈，线程私有

        操作系统为每个进程分配的内存是有限的
            stackSize 越大，可以创建的线程数量越少
            stackSize 越小，可以创建的线程数量越多
     */
    public static void main(String[] args) {
        Thread t = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    test();
                } catch (Error e) {
                    e.printStackTrace();
                    println("counter: " + counter); //counter: 37078
                }
            }

            /*
                java.lang.StackOverflowError
             */
            private void test() {
                counter++;
                test();
            }
        }, "Test", 1 << 24); // 8M

        t.start();
    }
}
