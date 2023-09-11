package com.z.p1.c04;

import static com.z.util.IO.println;

class DaemonThread {

    /*
        线程的生命周期
     */
    public static void main(String[] args) {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    println("run-before: " + getState()); //RUNNABLE
                    println(Thread.currentThread().getName() + " - BEFORE");
                    Thread.sleep(3000);//TIMED_WAITING
                    println(Thread.currentThread().getName() + " - AFTER");
                    println("run-after: " + getState()); //RUNNABLE
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };//NEW

        println("before start: " + t.getState());//NEW
        t.start();

        while (t.isAlive()) {
            println(t.getState().toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        println(Thread.currentThread().getName());
    }
}
