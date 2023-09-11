package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTest01 {

    /*
        AtomicInteger
            构造方法
            get
            set, 无锁，初始化
            getAndIncrement
            incrementAndGet
            getAndDecrement
            decrementAndGet

            getAndAdd
            getAndSet
     */
    public static void main(String[] args) {

        System.out.println("****************** ai");
        AtomicInteger ai = new AtomicInteger(10);
        System.out.println(ai.get());

        ai.set(20);
        System.out.println(ai.get());


        System.out.println("****************** getAndAdd");
        System.out.println(ai.getAndAdd(10));   // 20
        System.out.println(ai.get());           // 30

        System.out.println("****************** getAndSet");
        System.out.println(ai.getAndSet(60));   // 30
        System.out.println(ai.get());           // 60
    }
}
