package com.z.p3.c72;

import java.util.concurrent.*;

class Future05 {

    /*
        Attempts to cancel execution of this task.
        This attempt will fail if the task has already completed,
        has already been cancelled, or could not be cancelled for some other reason.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            System.out.println("------------------ task -> start");
            TimeUnit.SECONDS.sleep(2);
            return 10;
        });

        System.out.println("--------------------------- haha");
        System.out.println(future.get()); // block
        System.out.println(future.cancel(true)); // fail-1, 正常结束
    }
}
