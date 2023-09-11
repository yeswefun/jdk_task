package com.z.p3.c69;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ExecutorServiceTest00 {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = executor.schedule(() -> System.out.println("--- schedule"), 2, TimeUnit.SECONDS);
        System.out.println(future.cancel(true));
    }
}
