package com.z.p3.c52;

import java.util.concurrent.atomic.AtomicLong;

class MainTest01 {

    /*
        AtomicLong 除 VM_SUPPORTS_LONG_CAS 以外，其它的与 AtomicInteger 类似
        有的cpu只支持 32位的 cpu, 4字节
        long 8字节，使用两个 4字节模拟
     */
    public static void main(String[] args) {
        System.out.println("****************** ai0");
        AtomicLong ai0 = new AtomicLong();
        System.out.println(ai0.get());

        System.out.println("****************** ai1");
        AtomicLong ai1 = new AtomicLong(10);
        System.out.println(ai1.get());
    }
}
