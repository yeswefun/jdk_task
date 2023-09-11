package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadClose3 {

    private static class Worker extends Thread {
        @Override
        public void run() {
            int index = 0;
            while (!isInterrupted()) { // 非静态方法, Thread
//            while (!Thread.interrupted()) { // 静态方法, Runnable
                /*
                //InterruptedException 被 catch 了
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */
                println(Thread.currentThread().getName() + " -> index: " + (index++));
//                if (Thread.interrupted()) {
//                    break;
//                }
            }
            //break会跳转到此处
        }
    }

    /*
        判断标志
            Thread#isInterrupted()
            Thread#interrupted()
     */
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        w.start();
        Thread.sleep(5);
        w.interrupt();
    }
}