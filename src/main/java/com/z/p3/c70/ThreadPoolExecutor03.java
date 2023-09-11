package com.z.p3.c70;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class ThreadPoolExecutor03 {

    /*
        executorService.prestartCoreThread()
            预开启线程总数 < corePoolSize
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorService.getActiveCount()); // 0, 线程池启动后，默认不会创建任何线程

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());

        //如果存在空闲线程，就不会再启动线程，直接返回 true - 优化
        IntStream.range(0, 2).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> " + System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " -> finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());
    }
}
