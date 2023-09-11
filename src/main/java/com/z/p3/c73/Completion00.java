package com.z.p3.c73;

import java.util.concurrent.*;

class Completion00 {

    /*
        完成任务之后，没有回调
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 10;
        });
        System.out.println("-------------------");
        System.out.println(future.get()); // 缺点: 任务还没有完成，就会卡住
        System.out.println("------------------- over");
        executorService.shutdown();
    }
}
