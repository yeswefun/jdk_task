package com.z.p3.c70;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadPoolExecutor00 {

    /*
        第一季，自定义线程池
            init
            active
            max
            idleTime
            rejectPolicy
            queue

            线程启动时
                init 1，创建一个线程
                -> active 2,
                -> max 3,
                -> queue

        jdk内置线程池
            init, 0
            core
            max
            queue

            -> execute(runnable)
                1) corePoolSize
                    当前线程数 < corePoolSize, 直接开启新的线程执行任务
                2) 如果队列没有满，将任务放入队列
                    队列满了
                        当前线程数 是否大于 maxPoolSize
                            当前线程数 < maxPoolSize
                                开启(新的非核心)线程去执行任务
                            当前线程数 >= maxPoolSize
                                rejectPolicy
                                    AbortPolicy
                                    DiscardPolicy
                                    DiscardOldestPolicy
                                    CallerRunsPolicy
                线程池的的线程被终止的条件
                    线程池中的线程数 > corePoolSize && 线程的空闲时间 > keepAliveTime
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount()); // 没有 活动的(Active)，非守护线程(Daemon)

        executorService.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " -> " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executorService.getActiveCount());
    }
}
