package com.z.p3.c56;

class LatchTest01 {

    /*
        情况一: 单线程
            任务一, 耗时 2s
            任务二, 耗时 2s
            任务三, 耗时 4s
            总共耗时 8s

        情况二: 多线程
            任务一, 耗时 2s
            任务二, 耗时 2s
            任务三, 耗时 4s
            总共耗时 4s
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> task00());
        Thread t2 = new Thread(() -> task01());
        Thread t3 = new Thread(() -> task02());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    private static void task00() {
        sleep(2);
        System.out.println("task00");
    }

    private static void task01() {
        sleep(2);
        System.out.println("task01");
    }

    private static void task02() {
        sleep(4);
        System.out.println("task02");
    }

    private static void sleep(int s) {
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
