package com.z.p2.c26_rw;

import static com.z.util.IO.println;

class ReaderWorker extends Thread {

    private final SharedData2 data;

    public ReaderWorker(String name, SharedData2 data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                println(Thread.currentThread().getName() + " -> " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
