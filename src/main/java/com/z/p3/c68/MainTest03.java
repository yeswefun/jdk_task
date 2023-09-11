package com.z.p3.c68;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class MainTest03 {

    /*
        These pools will typically improve the performance
        of programs that execute many short-lived asynchronous tasks.

        每来一个任务都会开启一个新的线程 - 利于并发
            不适合耗时任务

        jps
        jstack ###
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> {
            System.out.println("-------------------- task");
        });
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
