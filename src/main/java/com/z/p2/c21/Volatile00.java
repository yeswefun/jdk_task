package com.z.p2.c21;

import static com.z.util.IO.println;

class Volatile00 {

    /*
        加不加 volatile 的对比
     */
    private static int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 16;

    public static void main(String[] args) {

        //第一个线程拿到 INIT_VALUE 的值存放到第一个线程的缓存中
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
//去掉注释后，也可以读取 INIT_VALUE 的最新值
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//java编译器做了优化，编译器发现线程中的代码只有对 INIT_VALUE 的读操作，而没有写操作，
//所以编译器认为线程不需要更新主内存，也不需要再到主内存中取值，
//导致线程一起到线程的缓存中获取 INIT_VALUE 值
                if (localValue != INIT_VALUE) {
                    println("update to " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }).start();

        //第二个线程拿到 INIT_VALUE 的值存放到第二个线程的缓存中
        //此处修改的是第二个线程的缓存中的 INIT_VALUE 的值
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                println("---> " + (++localValue));
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
