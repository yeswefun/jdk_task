package com.z.p2.c36;

import java.util.Random;

import static com.z.util.IO.println;

class MessageHandler {

    private static final Random r = new Random(System.currentTimeMillis());

    public void request(Message msg) {
        new Thread(() -> {
            String value = msg.getValue();
            try {
                Thread.sleep(r.nextInt(1000));
                println(Thread.currentThread().getName() + ", value: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
