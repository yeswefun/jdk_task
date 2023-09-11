package com.z.p1.c10;

import java.util.Collection;

interface MyLock {

    class TimeoutException extends Exception {
        public TimeoutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    Collection<Thread> getBlockedThreads();
    int getBlockedThreadSize();
}
