package com.z.p2.c21;

import static com.z.util.IO.println;

class Volatile01 {

    /*
        volatile关键字
            一旦一个共享变量被volatile修饰，具备两层语义
            1.保证了不同线程间的可见性
            2.禁止对其进行重排序，也就是保证了有序性
            3.并未保证原子性
     */
    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 16;

    public static void main(String[] args) {

        //第一个线程拿到 INIT_VALUE 的值存放到第一个线程的缓存中
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
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
