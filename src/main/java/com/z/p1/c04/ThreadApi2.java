package com.z.p1.c04;

import java.util.Optional;

class ThreadApi2 {

    /*
        Priority
            [1, 10]
            1 -> min
            5 -> norm
            10-> max

        优先级越大，只是表示获取得cpu时间片的概率大了而已
            具体还是要看操作系统内核的调度算法
     */
    public static void main(String[] args) {
        Thread t0 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                Optional.of(Thread.currentThread().getName() + " -> " + i).ifPresent(System.out::println);
            }
        });
        t0.setPriority(Thread.MAX_PRIORITY);
        t0.start();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                Optional.of("############ " + Thread.currentThread().getName() + " -> " + i).ifPresent(System.out::println);
            }
        });
        t1.setPriority(Thread.NORM_PRIORITY);
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                Optional.of("*************************************** " + Thread.currentThread().getName() + " -> " + i).ifPresent(System.out::println);
            }
        });
        t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();
    }
}
