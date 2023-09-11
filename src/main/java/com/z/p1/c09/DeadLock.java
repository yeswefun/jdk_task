package com.z.p1.c09;

import static com.z.util.IO.println;

class DeadLock {

    private final Object LOCK = new Object();

    private final DeadLockService deadService;

    public DeadLock(DeadLockService deadService) {
        this.deadService = deadService;
    }

    public void m1() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + ", m1 lock ---------> s1");
            deadService.s1();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            println(Thread.currentThread().getName() + ", m2 lock");
        }
    }
}
