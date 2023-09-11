package com.z.p2.c26_rw;

import java.util.Random;

class WriterWorker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final SharedData2 data;

    private final String filler;

    private int index = 0;

    public WriterWorker(String name, SharedData2 data, String filler) {
        super(name);
        this.data = data;
        this.filler = filler;
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
