package com.z.p1.c05;

class ThreadJoin4 {

    public static void main(String[] args) throws InterruptedException {
        /*
            主线程开启一个守护线程作为http服务
                守护线程：会随组内最后一个非守护线程的结束而结束
                    不占用端口，不占用资源

            主线程结束(守护线程也会结束)
                只有 主线程 和 一个守护线程 的情况
         */

        //主线程在等待主线程结束
        Thread.currentThread().join();
/*
        synchronized (ThreadJoin4.class) {
            while (Thread.currentThread().isAlive()) {
                Thread.currentThread().wait();
            }
        }
 */
    }
}
