package com.z.p3.c56;

class LatchTest00 {

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
    public static void main(String[] args) {
        task00();
        task01();
        task02();
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
