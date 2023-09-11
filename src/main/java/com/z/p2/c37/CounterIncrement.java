package com.z.p2.c37;

import java.util.Random;

import static com.z.util.IO.println;

class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;
    private final Random r = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                println(Thread.currentThread().getName() + " -> " + (counter++));
                Thread.sleep(r.nextInt(200));
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            //第二个阶段
            clean();
        }
    }

    //里面调用
    private void clean() {
        println("---------------------------> clean: " + Thread.currentThread().getName() + ", " + counter);
    }

    //外面调用
    public void close() {
        terminated = true;
        interrupt(); // Thread.sleep
    }
}
