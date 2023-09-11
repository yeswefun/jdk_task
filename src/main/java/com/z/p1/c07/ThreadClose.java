package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadClose {

    private static class Worker extends Thread {

        private volatile boolean flag = true;

        @Override
        public void run() {
            int index = 0;
            while (flag) {
                try {
                    println(Thread.currentThread().getName() + " -> index: " + (index++));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    flag = false;
                }
            }
        }

        public void shutdown() {
            flag = false;
        }
    }

    /*
        设置即出标志
     */
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        w.start();
        Thread.sleep(1500);
        w.shutdown();
    }
}