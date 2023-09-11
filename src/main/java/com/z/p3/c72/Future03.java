package com.z.p3.c72;

import java.util.concurrent.*;

class Future03 {

    /*
        Completion may be due to normal termination, an exception, or cancellation
        -- in all of these cases, this method will return true.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("------------------ submit -> task"); // timeout 之后，还会继续执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        System.out.println("--------------------------- haha");
        System.out.println(future.isDone());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(future.isDone());
    }
}
