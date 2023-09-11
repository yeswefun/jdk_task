package com.z.p3.c74;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

class Sche08 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AtomicLong interval = new AtomicLong(0L);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());

        executor.scheduleWithFixedDelay(
                () -> {
                    long ts = System.currentTimeMillis();
                    if (interval.get() == 0) {
                        System.out.println("the first time at " + ts);
                    } else {
                        System.out.println("the actual period: " + (ts - interval.get()));
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    interval.set(ts);
                    System.out.println(Thread.currentThread().getName());
                },
                1000, 500, TimeUnit.MILLISECONDS
        );

        TimeUnit.SECONDS.sleep(6);
        executor.shutdown();
        System.out.println("--- over");
    }
}
