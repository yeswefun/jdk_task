package com.z.p2.c30;

import static com.z.util.IO.println;

class MainTest00 {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    /*
        JVM start main thread
     */
    public static void main(String[] args) throws InterruptedException {
        tl.set("Jerry");
        Thread.sleep(1000);
        println(tl.get());
    }
}
