package com.z.p3.c74;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Sche02 {

    /*
        period 2sec execute a task
            situation-0
                jdk, Timer

            situation-1
                crontab
                quartz
                Control-M, 商业版
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> System.out.println("I'm lonely"), 1000, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(2000);
        System.out.println(future.cancel(true));
    }
}
