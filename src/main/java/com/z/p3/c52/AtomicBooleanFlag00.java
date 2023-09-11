package com.z.p3.c52;

import java.util.concurrent.atomic.AtomicBoolean;

class AtomicBooleanFlag00 {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    /*
        synchronized 会强制刷新一下 线程 的缓存
            System.out.println();
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                int index = 0;
                while (flag.get()) {
                    System.out.println(Thread.currentThread().getName() + " -> " + (index++));
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println("--- end while");
            }
        }.start();

        Thread.sleep(100);

        flag.set(false);
    }
}
