package com.z.p3.c51;

class JITTest01 {

    //强制到主内存中读取
    private static volatile boolean init = false;

    /*
        volatile 保证 可见性，有序性
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                //while (!init) {}
                //编译器 -> while(true) {}
                //编译器认为不会对 init 进行改变
                while (!init) {
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
