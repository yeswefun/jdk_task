package com.z.p1.c03;

class CreateThread6 {

    private static int counter = 0;

    /*
        操作系统为每个进程分配的内存是有限的
            stackSize 越大，可以创建的线程数量越少
            stackSize 越小，可以创建的线程数量越多

        Exception in thread "Thread-4059" java.lang.OutOfMemoryError: Java heap space

        默认的stackSize是多大?
     */
    public static void main(String[] args) {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                new Thread(() -> {
                    //栈帧宽度
                    byte[] data = new byte[1024 * 1024 * 10];
                    try {
                        Thread.sleep(60_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                ++counter;
            }
        } catch (Error e) {
            //ignore
        }
        System.out.println("counter: " + counter);
    }
}
