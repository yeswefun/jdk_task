package com.z.p3.c72;

import java.util.concurrent.*;

class Future04 {

    /*
        Completion may be due to normal termination, an exception, or cancellation
        -- in all of these cases, this method will return true.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            System.out.println("------------------ task -> start");
            if (true) {
                throw new RuntimeException("haha");
            }
            System.out.println("------------------ task -> end");
            return 10;
        });

        System.out.println("--------------------------- haha");
        System.out.println(future.isDone());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(future.isDone());
    }
}
