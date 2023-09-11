package com.z.p3.c70;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class ThreadPoolExecutor01 {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        //Core threads must have nonzero keep alive times
        executorService.setKeepAliveTime(3, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true); // comment, 注释之后，5个线程都不会被销毁

        IntStream.range(0, 5).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> " + System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " -> finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
