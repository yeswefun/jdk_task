package com.z.p2.c29;

import java.util.Random;

import static com.z.util.IO.println;

class ClientThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue) {
        this.queue = queue;
        this.sendValue = sendValue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 36; i++) {
            queue.putRequest(new Request(sendValue + ":" + i));
            println("client request -> " + sendValue + ":" + i);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
