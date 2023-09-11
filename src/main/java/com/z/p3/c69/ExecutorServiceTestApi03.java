package com.z.p3.c69;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class ExecutorServiceTestApi03 {

    /*
        想要获取执行结果
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(new MyTask(i) {
            @Override
            protected void error(Exception e) {
                System.out.println(no + " -> " + e.getMessage());
            }

            @Override
            protected void done() {
                System.out.println(no + " -> done");
            }

            @Override
            protected void doExecute() {
                if (no % 3 == 0) {
                    int r = no / 0;
                }
            }

            @Override
            protected void doInit() {
                //System.out.println(no + " -> doInit");
            }
        }));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("--------------------- main thread finished");
    }

    //模板方法
    //我们自定义的任务
    private static abstract class MyTask implements Runnable {

        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                doInit();
                doExecute();
                done();
            } catch (Exception e) {
                error(e);
            }
        }

        protected abstract void error(Exception e);

        protected abstract void done();

        protected abstract void doExecute();

        protected abstract void doInit();
    }

    //他人的任务
    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("t-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("===========================" + t.getName());
                System.out.println(cause.getMessage());
                //cause.printStackTrace();
            });
            return thread;
        }
    }
}
