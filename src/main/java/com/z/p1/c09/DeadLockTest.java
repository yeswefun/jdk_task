package com.z.p1.c09;

class DeadLockTest {

    /*
        死锁
            A持有B需要的锁，而B持有A需要的锁

        jps
        jstack PID

        死锁的解决
            超时重试
            ...
     */
    public static void main(String[] args) {

        /*
            DeadLock#m1 -> lock-1
                -> DeadLockService#s1
                    lock-2

            DeadLockService#s2 -> lock-2
                -> DeadLock#m2
                    lock-1
         */
        DeadLockService deadLockService = new DeadLockService();
        DeadLock deadLock = new DeadLock(deadLockService);
        deadLockService.setDeadLock(deadLock);

        //Thread-0
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }
            }
        }.start();

        //Thread-1
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLockService.s2();
                }
            }
        }.start();
    }
}
