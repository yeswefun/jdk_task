package com.z.p1.c08;

import static com.z.util.IO.println;

class SynchronizedThis2 {

    /*
        m1 与 m2 争的不是同一把锁
     */
    private static class ThisLock {

        private final Object LOCK = new Object();

        public synchronized void m1() {
            try {
                println(Thread.currentThread().getName());
                Thread.sleep(6_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void m2() {
            synchronized (LOCK) {
                try {
                    println(Thread.currentThread().getName());
                    Thread.sleep(6_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
        非静态同步方法，锁为 this
     */
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
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

