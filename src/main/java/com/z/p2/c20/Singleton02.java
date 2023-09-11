package com.z.p2.c20;

/*
推荐使用这种方式
    单例
    懒加载
    线程安全
    不加锁
 */
class Singleton02 {

    static {
        System.out.println("*** Singleton02");
    }

    private Singleton02() {
        System.out.println("@@@ Singleton02");
    }

    /*
        JVM保证线程安全
            主动加载

        类的主动加载和被动加载
     */
    private static class InstanceHolder {
        static {
            System.out.println("*** InstanceHolder");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //JVM会确定加载类时的线程安全
        private static final Singleton02 instance = new Singleton02();
    }

    public static Singleton02 getInstance() {
        //访问一个类的静态变量时，触发类的主动加载
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println("------> main");
        for (int i = 0; i < 16; i++) {
            System.out.println(Singleton02.getInstance());
        }
    }
}
