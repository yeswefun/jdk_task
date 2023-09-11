package com.z.p2.c37;

class MainTest00 {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement increment = new CounterIncrement();
        increment.start();

        Thread.sleep(1500);
        increment.close();
    }
}
