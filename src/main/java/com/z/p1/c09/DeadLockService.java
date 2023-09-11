package com.z.p1.c09;

import static com.z.util.IO.println;

class DeadLockService {

    private final Object LOCK = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + ", s1 lock");
        }
    }

    public void s2() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + ", s2 lock *********> m2");
            deadLock.m2();
        }
    }
}
