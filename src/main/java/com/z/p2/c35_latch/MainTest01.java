package com.z.p2.c35_latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static com.z.util.IO.println;

class MainTest01 {

    private static final Random r = new Random(System.currentTimeMillis());

    /*
        CountDown
            多个线程并发处理任务，统计耗时(以最后一个为准)

        IntStream.rangeClosed(1, 5)
                .forEach(i -> new Thread(() -> {
                    println(Thread.currentThread().getName() + " -> working");
                    try {
                        Thread.sleep(r.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }, String.valueOf(i)).start());
     */
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(4);

        //the first phase
        println("--------------- 开始第一阶段");
        for (int i = 0; i <= 5; i++) {
            Thread t = new Thread(() -> {
                println(Thread.currentThread().getName() + " -> working");
                try {
                    Thread.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }, String.valueOf(i));
            t.start();
        }

        latch.await();

        //the second phase
        println("--------------- 开始第二阶段");

        println("--------------- main thread finish");
    }
}
