package com.z.p2.c26_rw;


class MainTest01 {

    /*
        读写锁
            读共享
            写独占
            写优先

        问题: 读多写少，写线程很难抢到锁，读写互斥
            ReadWriteLock2(核心)

        读写分离
        ReadWriteLock design pattern
        Reader-Writer design pattern
     */
    public static void main(String[] args) {
        SharedData2 data = new SharedData2(10);
        new ReaderWorker("R1", data).start();
        new ReaderWorker("R2", data).start();
        new ReaderWorker("R3", data).start();
        new ReaderWorker("R4", data).start();
        new ReaderWorker("R5", data).start();
        new ReaderWorker("R6", data).start();
        new ReaderWorker("R7", data).start();
        new ReaderWorker("R8", data).start();
        new WriterWorker("W1", data, "abcdefghijk").start();
        new WriterWorker("W2", data, "ABCDEFGHIJK").start();
    }
}
