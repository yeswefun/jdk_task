package com.z.p3.c69;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class ExecutorServiceTestApi02 {

    /*
        获取不到结果，一个任务到底有没有出现错误?
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> {
            System.out.println(i / 0);
        }));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("--------------------- main thread finished");
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("t-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("===========================" + t.getName());
                System.out.println(cause.getMessage());
                //cause.printStackTrace();
            });
            return thread;
        }
    }
}
