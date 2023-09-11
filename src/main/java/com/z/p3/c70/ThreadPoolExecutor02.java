package com.z.p3.c70;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class ThreadPoolExecutor02 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

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

        //上面的 fixed 为 core: 2, max: 2, 执行两个任务
        TimeUnit.MILLISECONDS.sleep(200);
        Runnable r = () -> {
            System.out.println("never been called!");
        };
        executorService.execute(r); // 没有空闲的核心线程，必然要放入队列中
        System.out.println(executorService.remove(r));
    }
}
