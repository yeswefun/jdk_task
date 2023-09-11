package com.z.p3.c68;

import java.util.concurrent.*;

class MainTest00 {

    /*
        线程池七大参数
     */
    public static ThreadPoolExecutor getExecutor() {
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
                new ThreadPoolExecutor.AbortPolicy()
        );

        executor.execute(() -> sleepSeconds(500));
        executor.execute(() -> sleepSeconds(500));
        executor.execute(() -> sleepSeconds(500));
        executor.execute(() -> sleepSeconds(500));

        return executor;
    }

    public static void main(String[] args) {

        ThreadPoolExecutor executor = getExecutor();

        int activeCount = -1;
        int queueSize = -1;

        while (true) {
            if (activeCount != executor.getActiveCount() || queueSize != executor.getQueue().size()) {
                System.out.println("activeCount: " + executor.getActiveCount());
                System.out.println("queueSize: " + executor.getQueue().size());
                System.out.println("corePoolSize: " + executor.getCorePoolSize());
                System.out.println("maximumPoolSize: " + executor.getMaximumPoolSize());
                activeCount = executor.getActiveCount();
                queueSize = executor.getQueue().size();
                System.out.println("===================================================");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sleepSeconds(int millis) {
        try {
            System.out.println("-> " + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(millis);
            System.out.println("-> " + Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
