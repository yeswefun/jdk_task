package com.z.p1.c00_phase_1;

/*
第一阶段
    Thread
    ThreadGroup
    线程同步


第二阶段
    volatile
    多线程设计模式
    ClassLoader


第三阶段
    并发包
        Atomic
        Java并发包工具
        Executors
        并发集合
        Google Guava


第四阶段
    ...


A thread becomes the owner of the object's monitor in one of three ways:
    By executing a synchronized instance method of that object.
    By executing the body of a synchronized statement that synchronizes on the object.
    For objects of type Class, by executing a synchronized static method of that class.

    注: Only one thread at a time can own an object's monitor.



线程被唤醒
    Some other thread invokes the notify method for this object
    and thread T happens to be arbitrarily chosen as the thread to be awakened.

    Some other thread invokes the notifyAll method for this object.

    Some other thread interrupts thread T.

    The specified amount of real time has elapsed, more or less.
    If timeout is zero, however, then real time is not taken into consideration
    and the thread simply waits until notified.



wait的正确用法
    As in the one argument version, interrupts and spurious wakeups are possible,
    and this method should always be used in a loop:

    synchronized (obj) {
        while (<condition does not hold>)
            obj.wait(timeout, nanos);
        ... // Perform action appropriate to condition
    }



Runnable
    Being active simply means that a thread has been started
    and has not yet been stopped.

    In most cases, the Runnable interface should be used
    if you are only planning to override the run() method and no other Thread methods.
    This is important because classes should not be subclassed
    unless the programmer intends on modifying or enhancing
    the fundamental behavior of the class.


Thread
ThreadGroup
ThreadPool
    理解线程池原理

ThreadLocal<T>
    始终以当前线程作为key

Runtime
System
Process



相关命令
    jps

    jconsole PID
        查看线程状态
            另一种方式 run / dump threads

    jstack PID
        查看是否存在线程死锁的现象

    jar

Doug Lea
 */
interface ZSummary {
}
