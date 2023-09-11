package com.z.p3.c68;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class MainTest04 {

    /*
        new ThreadPoolExecutor(
            nThreads, nThreads,
            0L, TimeUnit.MILLISECONDS, // 当 corePoolSize == maxPoolSize时，这两个参数没有意义，不会终止线程
            new LinkedBlockingQueue<Runnable>()
        )

        只开10个线程执行任务
        线程不会自动关闭
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

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
