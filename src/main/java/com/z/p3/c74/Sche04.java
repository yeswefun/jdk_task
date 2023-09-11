package com.z.p3.c74;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

class Sche04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

        executor.scheduleWithFixedDelay(
                () -> System.out.println(System.currentTimeMillis()),
                1000, 500, TimeUnit.MILLISECONDS
        );
    }
}
