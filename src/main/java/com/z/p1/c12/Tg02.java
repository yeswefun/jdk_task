package com.z.p1.c12;

class Tg02 {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg0 = new ThreadGroup("tg0");
        tg0.setDaemon(true); // 注释去掉

        Thread t00 = new Thread(tg0, () -> {
            int index = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + " -> " + (index++));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " @@@@@@@@@@@@ is interrupted");
                    break;
                }
            }
        }, "t00");
        t00.start();

        Thread t01 = new Thread(tg0, () -> {
            int index = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + " ------> " + (index++));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " @@@@@@@@@@@@ is interrupted");
                    break;
                }
            }
        }, "t01");
        t01.start();

        ThreadGroup tg1 = new ThreadGroup(tg0, "tg1");
//        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t10 = new Thread(tg1, () -> {
            int index = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + " -> " + (index++));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " @@@@@@@@@@@@ is interrupted");
                    break;
                }
            }
        }, "t10");
        t10.start();

        ThreadGroup mainTg = Thread.currentThread().getThreadGroup();
        System.out.println("********************************* list-1");
        mainTg.list();
        System.out.println(mainTg.activeCount());
        System.out.println(mainTg.activeGroupCount());
        System.out.println("*********************************");

        Thread.sleep(1000);
        tg0.interrupt();
        Thread.sleep(500);

        System.out.println("********************************* list-1");
        mainTg.list();
        System.out.println(mainTg.activeCount());
        System.out.println(mainTg.activeGroupCount());
        System.out.println("*********************************");
    }
}
