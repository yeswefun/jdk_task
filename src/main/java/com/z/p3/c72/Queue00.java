package com.z.p3.c72;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Queue00 {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        BlockingQueue<Runnable> queue = executorService.getQueue(); // 是否可以往 queue 中加入 Runnable ，让其执行?

        //注释掉
        executorService.execute(() -> {
            System.out.println("execute");
        });

        //直接加入任务，没有提醒线程池有任务加入
        queue.add(() -> {
            System.out.println("---> haha");
        });
    }
}
