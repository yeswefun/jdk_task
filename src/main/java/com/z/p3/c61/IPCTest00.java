package com.z.p3.c61;

class IPCTest00 {

    private static int data = 0;

    private static boolean idle = false;

    private static final Object LOCK = new Object();

    /*
        produce
        consume
     */
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                produceData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                consumeData();
            }
        }).start();
    }

    private static void produceData() {
        synchronized (LOCK) {
            while (idle) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data++;
            System.out.println("P -> " + data);
            idle = true;
            LOCK.notifyAll();
        }
    }

    private static void consumeData() {
        synchronized (LOCK) {
            while (!idle) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C ------------> " + data);
            idle = false;
            LOCK.notifyAll();
        }
    }
}
