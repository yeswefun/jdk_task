package com.z.p2.c38;

class MainTest00 {

    /*
        Worker Thread Design Pattern
            类似 Producer and Consumer 和 Guarded Suspension

            Producer and Consumer
                速度不一致，queue 缓存
     */
    public static void main(String[] args) {

        Channel channel = new Channel(4);
        channel.start();

        new TransportThread("C", channel).start();
        new TransportThread("Go", channel).start();
        new TransportThread("Cpp", channel).start();
        new TransportThread("Java", channel).start();
    }
}
