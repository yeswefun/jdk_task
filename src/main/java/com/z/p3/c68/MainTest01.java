package com.z.p3.c68;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class MainTest01 {

    /*
        java -version

        jps -l

        jstack ####
     */
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
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        IntStream.range(0, 20).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            });
        });

        /*
            其它空闲线程标记为 interrupted, 工作的线程需要完成工作，才会关闭

            20 threads
                10 thread work
                10 idle thread

            shutdown
                10 idle thread interrupted
                10 work thread exit until finished the work
         */
//        executor.shutdown(); // 非阻塞

        /*
            等到所有并行任务都完成，才继续往下执行
                并行化 -> 串行化
         */
//        executor.shutdown(); // 非阻塞
//        try {
//            executor.awaitTermination(1, TimeUnit.HOURS); // 阻塞
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /*
            core: 10 threads
            queueSize: 10

            20 task
                1) 10 running
                2) 10 stored in blocking queue

            List<Runnable> -> 2)
            1) finish normally
         */
        try {
            List<Runnable> runnables = executor.shutdownNow();
            System.out.println(runnables.size());
        } catch (Exception e) {
            //ignore
        }
        System.out.println("--------------------------- main thread over");
    }
}
