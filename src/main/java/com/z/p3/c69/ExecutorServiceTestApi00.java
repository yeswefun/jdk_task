package com.z.p3.c69;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ExecutorServiceTestApi00 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println("run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown(); // comment
        System.out.println(executorService.isShutdown());

        //shutdown之后是否还可以执行Runnable? reject
        executorService.execute(() -> {
            System.out.println("run2");
        });
    }
}
