package com.z.p3.c74;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Sche01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        ScheduledFuture<Integer> future = executor.schedule(() -> 1, 1, TimeUnit.SECONDS);

        System.out.println("--- start");
        System.out.println(future.get());
        System.out.println("--- end");
    }
}
