package com.z.p3.c68;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class MainTest05 {

    /*
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();

        与 newSingleThreadExecutor 的区别
            单个线程执行完任务之后就会结束，但单个线程的线程池中的线程不会结束

            单个线程没有缓冲队列，而线程池可以

        new FinalizableDelegatedExecutorService(
            new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>()
            )
        );

        corePoolSize > 0，则线程池不会自动关闭，且 corePoolSize == maxPoolSize
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> {
            System.out.println("-------------------- task");
        });
//        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            });
        });
        TimeUnit.SECONDS.sleep(1);
//        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
