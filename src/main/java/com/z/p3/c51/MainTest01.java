package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class MainTest01 {

    private static AtomicInteger value = new AtomicInteger();

    /*
        CAS: Compare And Swap

        AtomicInteger
            可见性
            有序性
            原子性
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    value.getAndIncrement();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    value.getAndIncrement();
                }
            }
        };

        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println("------------> " + value.get());
    }
}
