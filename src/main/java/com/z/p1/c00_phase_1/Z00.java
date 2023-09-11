package com.z.p1.c00_phase_1;

/*
1.线程介绍
2.创建并启动线程
3.线程的生命周期
4.Runnable接口介绍
5.Thread API详细介绍
6.线程同步，锁技术
7.如何优雅的停止线程
8.线程间通讯
9.线程组详细介绍
10.线程池原理以及实现一-个简单的线程池
11.线程异常捕获以及线程堆栈信息详细讲解
12.FIFO队列以及多线程环境下的运行
13.BoolenL ock锁实现
14.常用设计模式在多线程环境下的使用
15.查缺补漏I


IO操作
    文件IO
    网络IO

并发操作
    进程间通信
    线程间同步

--------------------------------
线程

    一个进程至少有一个线程，1:1
    一个进程可以有多个线程，1:N


    进程与线程的区别
        进程是操作系统最小的资源分配单位
        线程是操作系统最小的运算调度单位

        一个应用由多个进程组成，一个进程崩溃不会影响其它进程
        一个应用由多个线程组成，一个线程崩溃会影响该线程所属的进程，从而影响跟线程在同一个进程的其它线程


        进程间通信，线程间同步


    注: 百度百科






线程生命周期





创建线程的两种方式
    继承 Thread，并重写 run 方法，实例化后调用 start 方法
    实现 Runnnable，并重写 run 方法，实例化后作为 Thread的构造方法参数实例化后 调用 start 方法


    继承 Thread 与 实现 Runnable 的区别




Runnable接口






Thread Api 详解

    构造方法

        ThreadGroup

        stackSize
            虚拟机栈
                线程私有的


    JVM内存模型
        程序计数器
        方法区
            所有线程共享
            栈上变量指向堆内存
        堆内存
            所有线程共享
            栈上变量指向堆内存
        虚拟机栈
            线程私有的
        本地方法区
            jni, c/c++




守护线程
    setDaemon
    isDaemon


Thread类方法
    getId
    getName
    getPriority
        线程优先级


    join


    interrupt

        sleep
        wait
        join

        isAlive


        重写 Thread#run
            this.isInterrupted();

        使用 Runnable 实现 作为 Thread构造参数
            Thread.interrupted();



    ThreadGroup


    synchronzied
        private final Object LOCK = new Object();
        this
        Xxx.class









jps

jconsole pid


jstack pid


 */
interface Z00 {
}
