package com.z.p3.c70;

import java.util.concurrent.*;
import java.util.stream.IntStream;

class RejectPolicy03 {

    /*
            core max queue
             1    2    1

         1:  1    2   empty, active-thread: 1
         2:  1    2   empty, active-thread: 2
         3:  1    2    1,    active-thread: 2, unhandled task
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 2,
                30, TimeUnit.SECONDS,
                blockingQueue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        return thread;
                    }
                },
                //new ThreadPoolExecutor.AbortPolicy() // 抛出异常
                //new ThreadPoolExecutor.DiscardPolicy() // 没有任何输出
                //new ThreadPoolExecutor.CallerRunsPolicy() // 哪个线程调用，在哪个线程执行任务
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        //max == 3
        IntStream.range(0, 3).boxed().forEach(i -> { // 0, 1, 2
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                }
            });
        });

        TimeUnit.SECONDS.sleep(1);
        executor.execute(() -> { // 提交任务失败
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("last task: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
            }
        });
    }
}
