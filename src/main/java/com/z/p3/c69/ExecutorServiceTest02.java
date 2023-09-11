package com.z.p3.c69;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ExecutorServiceTest02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> System.out.println("ts: " + System.currentTimeMillis()), 1, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(6);
        System.out.println(future.cancel(true));
    }
}
