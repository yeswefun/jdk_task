package com.z.p3.c74;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Sche00 {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        ScheduledFuture<?> future = executor.schedule(() -> System.out.println("invoked"), 1, TimeUnit.SECONDS);

        System.out.println(future.cancel(true)); // comment
    }
}
