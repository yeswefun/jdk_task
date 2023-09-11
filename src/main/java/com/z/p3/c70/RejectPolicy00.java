package com.z.p3.c70;

import java.util.concurrent.*;
import java.util.stream.IntStream;

class RejectPolicy00 {

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
//                        thread.setDaemon(true); // test-1
//                        thread.setDaemon(false); // okHttp
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        //max == 3
        IntStream.range(0, 3).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                } catch (InterruptedException e) {
                }
            });
        });

        TimeUnit.SECONDS.sleep(1);
        executor.execute(() -> { // 提交任务失败
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("last task");
            } catch (InterruptedException e) {
            }
        });
    }
}
