package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest00 {

    /*
        public Semaphore(int permits)

        public void acquire() throws InterruptedException

        public void release()

        //LOCK.lock();      //没有抛出异常
        try {
            LOCK.lock();    //抛出异常
        } finally {
            LOCK.unlock();
        }
     */
    public static void main(String[] args) {

        SemaphoreLock lock = new SemaphoreLock();

        new Thread() {
            @Override
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    lock.lock();
                    System.out.println(name + " ---> lock");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(name + " ------------------> " + i);
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    System.out.println(name + " ---> unlock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    lock.lock();
                    System.out.println(name + " ---> lock");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(name + " ------------------> " + i);
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    System.out.println(name + " ---> unlock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }

    static class SemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
