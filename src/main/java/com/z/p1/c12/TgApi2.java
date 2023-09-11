package com.z.p1.c12;

import java.util.Arrays;

import static com.z.util.IO.println;

class TgApi2 {

    public static void main(String[] args) {

        ThreadGroup tg1 = new ThreadGroup("TG-1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        Thread t11 = new Thread(tg1, "t11") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t11.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG-2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        Thread t22 = new Thread(tg2, "t22") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t22.start();

        println("************************ 0");
        println("" + tg1.activeCount()); // 2
        println("" + tg1.activeGroupCount()); // 1

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        println("************************ 3-1, tg: main");
        mainThreadGroup.list();

        println("************************ 3-2, tg: TG-1");
        tg1.list();

        println("************************ 3-3, tg: TG-2");
        tg2.list();

        println("************************ 4");
        println("" + tg1.parentOf(tg2));            // true
        println("" + tg1.parentOf(mainThreadGroup));// false
        println("" + mainThreadGroup.parentOf(tg1));// true
        println("" + mainThreadGroup.parentOf(tg2));// true

        Thread[] ts1;
        println("************************ 1");
        ts1 = new Thread[8];
        int cnt1 = mainThreadGroup.enumerate(ts1, false);      // 直系子Group
        //Arrays.asList(ts1).forEach(System.out::println);
        for (int i = 0; i < cnt1; i++) {
            System.out.println(ts1[i]);
        }

        println("************************ 2");
        ts1 = new Thread[8];
        int cnt2 = mainThreadGroup.enumerate(ts1, true);       // 所有子Group
        //Arrays.asList(ts1).forEach(System.out::println);
        for (int i = 0; i < cnt2; i++) {
            System.out.println(ts1[i]);
        }
    }
}
