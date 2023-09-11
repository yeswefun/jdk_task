package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerArray;

class ArrayTest10 {

    public static void main(String[] args) {
        AtomicIntegerArray arr = new AtomicIntegerArray(8);
        System.out.println(arr.length());
        System.out.println(arr);

        arr.set(6, 666);
        System.out.println(arr);
    }
}
