package com.z.p2.c34_pc;

import java.util.Random;

import static com.z.util.IO.println;

class ConsumerThread extends Thread {

    private final MessageQueue mq;

    private static final Random r = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue mq, int seq) {
        super("C-" + seq);
        this.mq = mq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = mq.take();
                println(Thread.currentThread().getName() + " ------------ " + msg.getData());
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
