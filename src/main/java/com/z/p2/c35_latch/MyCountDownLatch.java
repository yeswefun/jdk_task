package com.z.p2.c35_latch;

class MyCountDownLatch {

    private int counter = 0;

    private final int total;

    public MyCountDownLatch(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notify(); // 每次只唤醒一个
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                wait();
            }
        }
    }
}
