package com.z.p3.c69;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

class ExecutorServiceTest03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(
                () -> {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (interval.get() == 0) {
                        System.out.println("first time: " + currentTimeMillis);
                        interval.set(currentTimeMillis);
                    } else {
                        System.out.println("interval: " + (currentTimeMillis - interval.get()));
                    }
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                1, 2, TimeUnit.SECONDS
        );
    }
}
