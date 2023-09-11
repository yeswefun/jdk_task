package com.z.p3.c72;

import java.util.concurrent.*;

class Future01 {

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

        Thread callerThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callerThread.interrupt();
        }).start();

        Integer result = future.get(); // block, 主线程陷入阻塞，打断也只能打断主线程，而不是线程池中的线程

        //上面 interrupt 之后，就不会再往下执行了
        System.out.println("result: " + result);
    }
}
