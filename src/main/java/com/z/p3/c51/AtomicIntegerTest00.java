package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTest00 {

    /*
        AtomicInteger
            构造方法
            get
            set, 无锁，初始化
            getAndIncrement
            incrementAndGet
            getAndDecrement
            decrementAndGet
     */
    public static void main(String[] args) {

        System.out.println("****************** ai0");
        AtomicInteger ai0 = new AtomicInteger();
        System.out.println(ai0.get());

        System.out.println("****************** ai1");
        AtomicInteger ai1 = new AtomicInteger(10);
        System.out.println(ai1.get());

        ai1.set(20);
        System.out.println(ai1.get());


        System.out.println("****************** getAndIncrement");
        System.out.println(ai1.getAndIncrement());
        System.out.println(ai1.get());

        System.out.println("****************** incrementAndGet");
        System.out.println(ai1.incrementAndGet());
        System.out.println(ai1.get());


        System.out.println("****************** getAndDecrement");
        System.out.println(ai1.getAndDecrement());
        System.out.println(ai1.get());

        System.out.println("****************** decrementAndGet");
        System.out.println(ai1.decrementAndGet());
        System.out.println(ai1.get());

    }
}
