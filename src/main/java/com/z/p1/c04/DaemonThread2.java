package com.z.p1.c04;

import static com.z.util.IO.println;

class DaemonThread2 {

    /*
        A(Client) ---> B(Server)
            发送心跳检测

        整个过程客户端只有 主线程 和 A线程
            假设 A 是 非守护线程,
                主线程结束还需要另行通知 A 停止
                主线程结束，A 的存在导致 JVM 不会退出
            假设 A 是 守护线程，
                主线程结束，A 也会结束
     */
    public static void main(String[] args) {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    println(Thread.currentThread().getName() + " - BEFORE");
                    Thread.sleep(1000 * 100);
                    println(Thread.currentThread().getName() + " - AFTER");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //同一个线程组内的所有非守护线程结束，该线程组内的其它守护线程也会结束
        //main线程结束，main线程创建的守护线程也会结束
        t.setDaemon(true);
        t.start();

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println(Thread.currentThread().getName() + " ---> finish");
    }
}
