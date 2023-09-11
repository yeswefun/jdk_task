package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerArray;

class ArrayTest11 {

    public static void main(String[] args) {
        int[] a = new int[8];
        AtomicIntegerArray arr = new AtomicIntegerArray(a);
        System.out.println(arr);

        int oldVal = arr.getAndSet(6, 666);
        System.out.println(oldVal);
        System.out.println(arr);
        System.out.println(arr.get(6));
    }
}
