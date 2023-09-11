package com.z.p3.c72;

import java.util.concurrent.*;

class Future00 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // work 1
        // work 2
        // work 3, result from work 1
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        System.out.println("--------------------------- haha");

        Integer result = future.get(); // block
        System.out.println("result: " + result);
    }
}
