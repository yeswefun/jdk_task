package com.z.p3.c52;

import java.util.concurrent.atomic.AtomicBoolean;

class AtomicBooleanFlag01 {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    /*
        synchronized 会强制刷新一下 线程 的缓存
            System.out.println();
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (flag.get()) {
                }
                System.out.println("--- end while");
            }
        }.start();

        Thread.sleep(100);

        flag.set(false);
    }
}
