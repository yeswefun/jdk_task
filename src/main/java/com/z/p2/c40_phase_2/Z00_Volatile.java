package com.z.p2.c40_phase_2;

/*
2.WaitSet in synchronized monitor

    调用 XXX.wait(); 之前，必须先调用 synchronized(XXX)

    线程执行到一个同步代码块的时候，阻塞，释放锁

    1. 所有的对象都会有一个wait-set, 用来存放调用了该对象wait方法之后进入blocked状态线程
    2. 线程被notify之后，不一定立即得到执行(必须先抢到锁才能得到执行权)
    3. 线程从wait-set中被唤醒顺序不一定是FIFO
    4. 线程被唤醒后，必须重新获取锁


3.Cpu&Cpu cache&Main Memory&Data Bus&Cache Line

19.JMM-Java Memory Model

4.The volatile key word in deep
 */
interface Z00_Volatile {
}
