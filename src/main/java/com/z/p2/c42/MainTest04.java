package com.z.p2.c42;

import static com.z.util.IO.println;

class MainTest04 {

    private static class Simple {

        static {
            println("------------------ before");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println("------------------ after");
        }

        public Simple() {
            println(Thread.currentThread().getName());
        }
    }

    /*
        虚拟机有义务保证<clinit>()方法的线程安全
     */
    public static void main(String[] args) {
        new Thread(() -> new Simple()).start();
        new Thread(() -> new Simple()).start();
        println("------------------ main thread finished!");
    }
}
