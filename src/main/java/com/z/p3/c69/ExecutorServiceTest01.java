package com.z.p3.c69;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ExecutorServiceTest01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<Integer> future = executor.schedule(() -> 2, 3, TimeUnit.SECONDS);
        System.out.println(future.get()); //阻塞
    }
}
