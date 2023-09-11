package com.z.p3.c71;

import java.util.concurrent.*;

class Submit01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //为什么是 ?, 因为 Runnable 没有返回值
        String result = "DONE";
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);

        //Runnable是否结束
        //阻塞，直到 Runnable 执行结束
        System.out.println("----------------- finished: " + future.get());
    }
}
