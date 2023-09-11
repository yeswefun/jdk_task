package com.z.p1.c05;

import java.util.stream.IntStream;

class ThreadJoin {

    /*
        join阻塞的是当前线程
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            IntStream.range(1, 200)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
        });
        t.start();

        //执行完t之后，再继续执行主线程
        t.join();

        IntStream.range(1, 200)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
