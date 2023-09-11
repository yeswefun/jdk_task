package com.z.p1.c05;

import static com.z.util.IO.println;

class ThreadJoin6 {

    private static class CaptureRunnable implements Runnable {

        private String machineName;
        private long spendTime;

        public CaptureRunnable(String machineName, long spendTime) {
            this.machineName = machineName;
            this.spendTime = spendTime;
        }

        @Override
        public void run() {
            //采集工作
            try {
                Thread.sleep(spendTime);
                println("machineName: " + machineName + " finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTs = System.currentTimeMillis();

        Thread t1 = new Thread(new CaptureRunnable("M1", 1000));
        Thread t2 = new Thread(new CaptureRunnable("M2", 2000));
        Thread t3 = new Thread(new CaptureRunnable("M3", 3000));
        t1.start();
        t2.start();
        t3.start();

        //等待三个join中最长的那个
        println("t1 join");
        t1.join();
        println("t2 join");
        t2.join();
        println("t3 join");
        t3.join();

        //统计最长耗时
        long endTs = System.currentTimeMillis();
        println("ms: " + (endTs - startTs));
    }
}

