package com.z.p2.c20;

class Singleton01Fix3 {

    /*
        空指针异常解决
        编译时优化 - 重排序
        运行时优化

        volatile
            并不能保证原子性，可以保证内存可见性，有序性
            happpens-before
            volatile禁止了一些优化

        DCL + volatile
            单例
            懒加载
            线程安全
     */
    private static volatile Singleton01Fix3 instance;

    /*
        instance 已经指向在堆中内存
        但还没有调用构造方法
     */
    private Singleton01Fix3() {
        //int i = 0;
        //int j = 10;
        //结果: i = 0, j = 10, 但是顺序不确定，只有结果是确定的
        //初始化做一些耗时操作，可能会导致空指针?
    }

    /*
        每次读操作，都需要进行加锁
            锁的力度大

        分析三个线程同时调用 getInstance 的情景
     */
    public static Singleton01Fix3 getInstance() {
        if (instance == null) {
            synchronized (Singleton01Fix3.class) {
                if (instance == null) {
                    instance = new Singleton01Fix3();
                }
            }
        }
        return Singleton01Fix3.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(Singleton01Fix3.getInstance());
        }
    }
}
