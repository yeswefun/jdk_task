package com.z.p2.c38;

import java.util.Random;

class TransportThread extends Thread {

    private final Channel channel;

    private static final Random r = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            int index = 0;
            while (true) {
                Request request = new Request(getName(), index++);
                channel.put(request);
                Thread.sleep(r.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
