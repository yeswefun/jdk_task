package com.z.p2.c25_rw;

class MainTest00 {

    /*
        读写锁
            读共享
            写独占
            写优先

        问题: 读多写少，写线程很难抢到锁，读写互斥
     */
    public static void main(String[] args) {
        SharedData data = new SharedData(10);
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
