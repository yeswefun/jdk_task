package com.z.p2.c21;

class Volatile03 {

    /*
        加不加 volatile 的对比
            volatile 保证 内存的可见性 和 程序执行的有序性
                不能保证原子性
            happens-before?

        cpu寄存器
        cpu指令，机器码
            汇编也要变成cpu指令才能被执行
        程序计数器
            向cpu发送字节码对应的机器码

        cpu运算速度快，内存运算慢
     */
    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 16;

    /*
        下面的例子为什么不是各自加了16次?
            因为线程中会对变量进行修改，修改会更新到主存中

        volatile无法保证原子性
     */
    public static void main(String[] args) {

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                //此处有写操作，所以会更新到主内存
                System.out.println("T1 -> " + (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                //此处有写操作，所以会更新到主内存
                System.out.println("T2 -> " + (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
