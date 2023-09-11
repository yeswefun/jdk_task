package com.z.p3.c68;

import java.util.concurrent.*;
import java.util.stream.IntStream;

class MainTest02 {

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20,
                30, TimeUnit.SECONDS,
                blockingQueue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true); // test-1
//                        thread.setDaemon(false); // okHttp
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 10).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            });
        });

        executor.shutdown(); //test-1, 等待工作线程完成任务才关闭线程池
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS); // 超过3秒，工作线程没有完成任务，还是不会关闭线程池
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        executor.shutdownNow(); //test-2, run next right now
        System.out.println("--------------------------- main thread over");
    }
}
