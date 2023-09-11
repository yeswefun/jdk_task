package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadClose2 {

    private static class Worker extends Thread {
        @Override
        public void run() {
            int index = 0;
            while (true) {
                try {
                    println("index: " + (index++));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                    //return;
                }
            }
            //break会跳转到此处
        }
    }

    /*
        Thread#interrupt()
     */
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        w.start();
        Thread.sleep(1500);
        w.interrupt();
    }
}