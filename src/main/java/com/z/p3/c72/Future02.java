package com.z.p3.c72;

import java.util.concurrent.*;

class Future02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // work 1
        // work 2
        // work 3, result from work 1
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("------------------ submit -> task"); // timeout 之后，还会继续执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        System.out.println("--------------------------- haha");

        Integer result = future.get(1, TimeUnit.SECONDS); // block, 主线程陷入阻塞，打断也只能打断主线程，而不是线程池中的线程

        System.out.println("result: " + result);
    }
}
