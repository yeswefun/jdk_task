package com.z.p3.c52;

import java.util.concurrent.atomic.AtomicBoolean;

class MainTest00 {

    public static void main(String[] args) {

        AtomicBoolean a0 = new AtomicBoolean();
        System.out.println(a0.get());

        AtomicBoolean a1 = new AtomicBoolean(true);
        System.out.println(a1.get());

        System.out.println("****************** compareAndSet -> true");
        System.out.println(a1.compareAndSet(true, false));
        System.out.println(a1.get());

        System.out.println("****************** compareAndSet -> false");
        System.out.println(a1.compareAndSet(false, true));
        System.out.println(a1.get());

        System.out.println("****************** set");
        a1.set(false);
        System.out.println(a1.get());

        System.out.println("****************** getAndSet");
        System.out.println(a1.getAndSet(true));
        System.out.println(a1.get());
    }
}
