package com.z.p3.c52;

class AtomicBooleanFlag02 {

//    private static volatile boolean flag = true;
    private static boolean flag = true;

    /*
        synchronized 会强制刷新一下 线程 的缓存
            System.out.println();
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                }
                System.out.println("--- end while");
            }
        }.start();

        Thread.sleep(100);

        System.out.println("---> flag: " + flag);
        flag = false;
        System.out.println("---> flag: " + flag);
    }
}
