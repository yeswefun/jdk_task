package com.z.p3.c70;

import java.util.concurrent.*;
import java.util.stream.IntStream;

class ThreadPoolExecutor05 {

    /*
        executorService.prestartCoreThread()
            预开启线程总数 < corePoolSize
     */
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new MyThreadPoolExecutor(
                10, 20,
                30, TimeUnit.SECONDS,
                blockingQueue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 4).boxed().forEach(i -> {
            executor.execute(new MyTask(i) {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName()); // ok
                    System.out.println(1 / 0); // err
                }
            });
        });
    }


    //模板方法
    //我们自定义的任务
    private static abstract class MyTask implements Runnable {

        protected final int no;

        protected MyTask(int no) {
            this.no = no;
        }

        protected int getNo() {
            return no;
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            //super.beforeExecute(t, r);
            System.out.println("*** beforeExecute: " + ((MyTask) r).getNo());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            //super.afterExecute(r, t);
            if (t == null) {
                System.out.println("*** afterExecute: " + ((MyTask) r).getNo());
            } else {
                t.printStackTrace();
            }
        }
    }
}
