package com.z.p3.c57;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class CyclicBarrierTest20 {

    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    /*
        CountDownLatch 引入 回调机制
     */
    public static void main(String[] args) {
        MyCountDownLatch latch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all worker thread finish");
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " ---> finish");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " ---> finish");
            }
        }.start();
    }
}
