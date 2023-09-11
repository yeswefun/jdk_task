package com.z.p1.c08;

import static com.z.util.IO.println;

class SynchronizedThis {

    /*
        m1，m2，m3 争的是同一把锁
     */
    private static class ThisLock {

        public ThisLock() {
            new Thread(() -> {
                synchronized (this) {
                    println("invoke <init>");
                    try {
                        println(Thread.currentThread().getName() + " ---> lock");
                        Thread.sleep(3_000);
                        println(Thread.currentThread().getName() + " ---> unlock");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        public void m0() {
            synchronized (this) {
                println("invoke m0");
                try {
                    println(Thread.currentThread().getName());
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void m1() {
            println("invoke m1");
            try {
                println(Thread.currentThread().getName());
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //       public void m2() {
        public synchronized void m2() {
            println("invoke m2");
            try {
                println(Thread.currentThread().getName());
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        非静态同步方法，锁为 this
     */
    public static void main(String[] args) {

        println("*** this lock before");
        ThisLock thisLock = new ThisLock();
        println("*** this lock after");

        new Thread("T0") {
            @Override
            public void run() {
                thisLock.m0();
            }
        }.start();

        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}