package com.z.p3.c51;

class MainTest00 {

    private static volatile int value = 0;

    /*
        volatile
            可见性
            有序性
            不能保证原子性

        AtomicInteger
            volatile value
                可见性
                有序性
            CAS - 原子性
     */
    public static void main(String[] args) throws InterruptedException {
        /*
            1) main memory -> local memory
            2) add 1 to result
            3) assign result to value
            3) flush value to main memory
         */
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    value++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    value++;
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("------------> " + value);
    }
}
