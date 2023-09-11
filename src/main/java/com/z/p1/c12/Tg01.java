package com.z.p1.c12;

class Tg01 {

    public static void main(String[] args) {

        ThreadGroup tg0 = new ThreadGroup("tg-0");

        Thread t00 = new Thread(tg0, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t00");
        t00.start();

        Thread t01 = new Thread(tg0, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t01");
        t01.start();

        ThreadGroup tg = new ThreadGroup(tg0, "tg-1");

        Thread t1 = new Thread(tg, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(tg, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();

        System.out.println("********************************* list");
        ThreadGroup mainTg = Thread.currentThread().getThreadGroup();
        mainTg.list();
        System.out.println(mainTg.activeCount());
        System.out.println(mainTg.activeGroupCount());

        Thread[] tArr;
        int cnt;
        System.out.println("********************************* int enumerate(Thread[] list)");
        tArr = new Thread[16];
        cnt = mainTg.enumerate(tArr);
        System.out.println("cnt: " + cnt);
        for (int i = 0; i < cnt; i++) {
            System.out.println(tArr[i]);
        }

        System.out.println("********************************* int enumerate(Thread[] list, boolean recurse)");
        tArr = new Thread[16];
        cnt = mainTg.enumerate(tArr, false);
        System.out.println("cnt: " + cnt);
        for (int i = 0; i < cnt; i++) {
            System.out.println(tArr[i]);
        }

        ThreadGroup[] tgArr;
        int gCnt;
        System.out.println("********************************* int enumerate(ThreadGroup[] list)");
        tgArr = new ThreadGroup[16];
        gCnt = mainTg.enumerate(tgArr);
        System.out.println("gCnt: " + gCnt);
        for (int i = 0; i < gCnt; i++) {
            System.out.println(tgArr[i]);
        }

        System.out.println("********************************* int enumerate(ThreadGroup[] list, boolean recurse)");
        tgArr = new ThreadGroup[16];
        gCnt = mainTg.enumerate(tgArr, false);
        System.out.println("gCnt: " + gCnt);
        for (int i = 0; i < gCnt; i++) {
            System.out.println(tgArr[i]);
        }
    }
}
