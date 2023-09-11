package com.z.p3.c59;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreTest02 {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(2);

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        sem.acquire(2);
                        System.out.println(name + " ---> lock");
                        System.out.println(name + " ---------> doing something ...");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        sem.release(2);
                        System.out.println(name + " ---> unlock");
                    }
                }
            }.start();
        }

    }
}
