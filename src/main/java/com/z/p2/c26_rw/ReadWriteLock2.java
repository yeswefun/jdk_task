package com.z.p2.c26_rw;

class ReadWriteLock2 {

    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0; //最多只有一个
    private int waitingWriters = 0;

    //****************************** modified
    private boolean preferWriter;

    public ReadWriteLock2() {
        this(true);
    }

    public ReadWriteLock2(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
    //****************************** modified

    public synchronized void readLock() throws InterruptedException {
        waitingReaders++;
        try {
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) { // modified
                wait();
            }
            readingReaders++;
        } finally {
            waitingReaders--;
        }
    }

    public synchronized void readUnlock() {
        readingReaders--;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
            writingWriters++;
        } finally {
            waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        writingWriters--;
        notifyAll();
    }
}
