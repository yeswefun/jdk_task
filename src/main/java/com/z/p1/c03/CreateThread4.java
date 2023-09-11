package com.z.p1.c03;

import static com.z.util.IO.println;

class CreateThread4 {

    private static int counter = 0;

    /*
        JVM创建一个线程执行main方法
            创建一个虚拟机栈，线程私有

        在子线程中
     */
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
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
        });

        t.start();
    }
}
