package com.z.p1.c12;

class Tg00 {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup tg = new ThreadGroup("tg-1");

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

        Thread.sleep(200);
        System.out.println("********************************* tg");
        System.out.println(tg.getName());
        System.out.println(tg.getMaxPriority());
        System.out.println(tg);

        System.out.println("********************************* mainTg");
        ThreadGroup mainTg = Thread.currentThread().getThreadGroup();
        System.out.println(mainTg.getName());
        System.out.println(mainTg.getMaxPriority());
        System.out.println(mainTg);

        System.out.println("********************************* relationship");
        System.out.println(tg.getParent());
        System.out.println(mainTg.parentOf(tg));

        System.out.println("********************************* count & list -> tg");
        System.out.println(tg.activeCount());
        System.out.println(tg.activeGroupCount());
        tg.list();

        System.out.println("********************************* count & list -> mainTg");
        System.out.println(mainTg.activeCount());
        System.out.println(mainTg.activeGroupCount());
        mainTg.list();
    }
}
