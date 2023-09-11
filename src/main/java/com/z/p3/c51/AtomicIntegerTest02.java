package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTest02 {

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

            Number
                intValue
                longValue
                floatValue
                doubleValue
     */
    public static void main(String[] args) {

        System.out.println("****************** ai");
        AtomicInteger ai = new AtomicInteger(10);
        System.out.println(ai.get());

        System.out.println("****************** intValue");
        System.out.println(ai.intValue());

        System.out.println("****************** longValue");
        System.out.println(ai.longValue());

        System.out.println("****************** floatValue");
        System.out.println(ai.floatValue());

        System.out.println("****************** doubleValue");
        System.out.println(ai.doubleValue());
    }
}
