package com.z.p1.c05;

import java.util.stream.IntStream;

class ThreadJoin2 {

    /*
        join阻塞的是当前线程
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 100)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
        });

        Thread t2 = new Thread(() -> {
            IntStream.range(1, 100)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
        });

        t1.start();
        t2.start();

        //执行完t1,t2之后，再继续执行主线程
        long start = System.currentTimeMillis();

        System.out.println("t1#isAlive(): " + t1.isAlive() + ", ms: " + (System.currentTimeMillis() - start));
        t1.join();
        System.out.println("t1#isAlive(): " + t1.isAlive() + ", ms: " + (System.currentTimeMillis() - start));

        for (int i = 0; i < 16; i++) {
            Thread.sleep(200);
            System.out.println("-----------------------------------------" + i);
        }
        System.out.println("t2#isAlive(): " + t2.isAlive() + ", ms: " + (System.currentTimeMillis() - start));
        t2.join();
        System.out.println("t2#isAlive(): " + t2.isAlive() + ", ms: " + (System.currentTimeMillis() - start));

        System.out.println("********************* execute main thread");
        IntStream.range(1, 100)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
