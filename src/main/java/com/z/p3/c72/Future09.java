package com.z.p3.c72;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Future09 {

    /*
        Attempts to cancel execution of this task.
        This attempt will fail if the task has already completed,
        has already been cancelled, or could not be cancelled for some other reason.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            //TimeUnit.SECONDS.sleep(2); // 被取消后，就不再往下继续执行，因为线程还活着
            while (running.get()) {

            }
            System.out.println("task - finished"); // 没有输出
            return 10;
        });

        System.out.println("--------------------------- haha");
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(future.cancel(true)); // true
        System.out.println(future.cancel(true)); // fail-2, 已经被取消

        System.out.println(future.isDone()); // true
        System.out.println(future.isCancelled()); // true
    }
}
