package com.z.p3.c51;

import java.util.concurrent.atomic.AtomicInteger;

class CASLock {

    /*
        0: 空闲
        1: 被占用
     */
    private final AtomicInteger value = new AtomicInteger();
    private Thread thread;

    public void tryLock() throws GetLockFailedException {
        if (!value.compareAndSet(0, 1)) {
            throw new GetLockFailedException("************ get lock failed: " + Thread.currentThread().getName());
        }
        thread = Thread.currentThread();
    }

    /*
        只有持有锁的线程才能解锁
     */
    public void unlock() {
        if (value.get() == 0 || Thread.currentThread() != thread) {
            return;
        }
        value.compareAndSet(1, 0);
    }

    static class GetLockFailedException extends Exception {

        public GetLockFailedException() {
        }

        public GetLockFailedException(String message) {
            super(message);
        }
    }
}
