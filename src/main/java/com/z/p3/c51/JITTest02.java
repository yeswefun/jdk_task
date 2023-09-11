package com.z.p3.c51;

class JITTest02 {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (!init) {
                    System.out.println("."); // synchronized 强制刷新线程缓存
                }
            }
        }.start();

        Thread.sleep(200);

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("********************* set init to true");
            }
        }.start();
    }
}
