package com.z.p2.c34_pc;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.z.util.IO.println;

class ProducerThread extends Thread {

    private final MessageQueue mq;

    private static final Random r = new Random(System.currentTimeMillis());

    private static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue mq, int seq) {
        super("P-" + seq);
        this.mq = mq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = new Message("msg-" + counter.getAndIncrement());
                mq.put(msg);
                println(Thread.currentThread().getName() + " + " + msg.getData());
                Thread.sleep(r.nextInt(300) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
