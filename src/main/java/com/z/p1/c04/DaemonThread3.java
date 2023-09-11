package com.z.p1.c04;

import static com.z.util.IO.println;

class DaemonThread3 {
    /*
        问题:
            主线程结束，子线程A是非守护线程，子线程A开启子线程B，B是守护线程，那么A结束，B会不会也结束?
                会
            主线程结束，子线程A是非守护线程，子线程A开启子线程B，B是非守护线程，那么A结束，B会不会也结束?
                不会
     */
    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            Thread tt = new Thread(() -> {
                int index = 0;
                try {
                    while (true) {
                        Thread.sleep(500);
                        println("tt Thread send packet: " + (index++));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            tt.setDaemon(true); // 注释后，t线程结束, 但tt没有随着t的结束而结束
            tt.start();

            try {
                Thread.sleep(1000); // t -> sleep -> finish
                println("****************** t thread terminated");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        //主线程退出，但是由于主线程所在的线程组内还有非守护线程，所以JVM不会退出
        println("################## main thread terminalted");
    }
}
