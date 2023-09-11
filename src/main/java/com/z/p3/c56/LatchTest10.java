package com.z.p3.c56;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LatchTest10 {

    private static final Random r = new Random();

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    /*
        统计耗时(以最后一个作为终止)
     */
    public static void main(String[] args) throws InterruptedException {
        int[] data = query();
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("main ---> finish task!");
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;

        private final int index;

        public SimpleRunnable(int[] data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //对data的某个index操作
            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 100;
            }

            System.out.println(Thread.currentThread().getName() + " ---> finish task[" + index + "]!");
        }
    }
}
