package com.z.p2.c30;

import static com.z.util.IO.println;

class MainTest01 {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    /*
        JVM start main thread
     */
    public static void main(String[] args) throws InterruptedException {
        println(tl.get());
    }
}
