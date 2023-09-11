package com.z.p3.c71;

import java.util.concurrent.*;

class Submit00 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //为什么是 ?, 因为 Runnable 没有返回值
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Object o = future.get();// 阻塞，直到 Runnable 执行结束
        System.out.println("----------------- finished: " + o);
    }
}
