package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTest03 {

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

            CAS: compareAndSet
     */
    public static void main(String[] args) {

        System.out.println("****************** ai");
        AtomicInteger ai = new AtomicInteger(10);
        System.out.println(ai.get());

        System.out.println("****************** compareAndSet -> false");
        System.out.println(ai.compareAndSet(666, 888));
        System.out.println(ai.get());

        System.out.println("****************** compareAndSet -> true");
        System.out.println(ai.compareAndSet(10, 20));
        System.out.println(ai.get());
    }
}
