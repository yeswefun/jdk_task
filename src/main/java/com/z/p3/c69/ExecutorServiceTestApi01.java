package com.z.p3.c69;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ExecutorServiceTestApi01 {

    /*
        ExecutorService#isTerminated()
        ThreadPoolExecutor#isTerminating()

        Executors.newSingleThreadExecutor()
            只暴露了 ExecutorService 的方法
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown()); // false
        executorService.shutdown();
        System.out.println(executorService.isShutdown()); // true

        System.out.println(((ThreadPoolExecutor)executorService).isTerminating()); //true
        System.out.println(executorService.isTerminated()); // false
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executorService.isTerminated()); // true
    }
}
