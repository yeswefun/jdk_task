package com.z.p2.c21;

/*
1.cpu的大致结构
    cpu = 控制器 + 运算器


2.JMM的大致结构，Java Memory Model
    cpu指令，机器码
        汇编也要变成cpu指令才能被执行
    程序计数器
        向cpu发送字节码对应的机器码

    cpu运算速度快，内存运算慢
        线程中的缓存


3.缓存一致性协议

    cpu缓存不一致的问题

    i = i + 1;
        main memory -> i -> cache -> i+1 -> cache -> main memory

    在 单线程 的情况下没有问题，但在 多线程 的情况有问题
        cpu1 -> main memory -> i -> cache -> i+1 -> cache(2) -> main memory(2)
        cpu2 -> main memory -> i -> cache -> i+1 -> cache(2) -> main memory(2)
        注: 也可以是同一个cpu内的两个线程

    两种解决方式
        给数据总线加锁
            总线(cpu与其它硬件设备都是通过总线进行通信)
                数据总线
                地址总线
                控制总线
            cpu1 对 总线 进行 加锁
                加锁之后，其它 cpu 不能访问与该总线对应的内存
                从而保证，只有一个 cpu 能访问这个变量的值
                缺点: 串行化，慢

        cpu高速缓存一致性协议
            intel, MESI
            核心思想
                1.如果发现该变量被共享(也就是说，在其他cpu中 也存在该变量的副本).
                  会发出一一个信号。 通知其他cpu该变量的缓存无效
                2.当其他cpu访问该变量的时候，重新到主内存进行获取


4.并发编程的三大要素(原子性，可见性，有序性)

    原子性，Atomic
        一个操作或者多个操作，要么都成功。要么都失败，中间不能由于任何的因素终端

    可见性，Visibility
        主内存: int i = 0;

        Thread-1                Thread-2
        (先将i从主存->线程缓存)    int j = i;
        i = 10;
        cache(i = 10)           cache(j = 0, maybe)

        可见性结果:
            i = 10, j = 10

    有序性，Order
        {
            int i = 0;
            boolean flag = false;
            i = 1;
            flag = true;
        }

        指令重排序
            编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序

            重排序分为两大类
                一是编译器重排序
                    编译器优化重排序
                二是处理器重排序
                    指令级并行重排序
                    内存系统重排序

            指令重排序 对 单线程 没有造成问题，问题出现在 多线程 的情况

        上面的例子发生指令重排序后
        {
            int i = 0;
            i = 1; //存在依赖关系的，需要保证顺序执行(先定义，再赋值)
            boolean flag = false;
            flag = true; //存在依赖关系的，需要保证顺序执行(先定义，再赋值)
        }

        指令重排序只要求最终一致性
            i = 1;
            flag = true;

        指令重排序例子
            --------------- Thread-1 ---------------
            obj = createObj();
            init = true;
            --------------- Thread-2 ---------------
            while(!init) {
                sleep();
            }
            useTheObj(obj);

            Thread-1发生重排序
            --------------- Thread-1 ---------------
            init = true;
            obj = createObj();


5.happens-before规则
    1. 代码的执行顺序，编写在前面的发生在编写在后面的前面
    2. unlock必须发正在lock之后
    3. volatile修饰的变量，对一个变量的写操作先于对该变量的读操作。
    4. 传递规则，操作A先于B, B先于c，那么A肯定先于c
    5. 线程启动规则，start方法肯定先于线程run
    6. 线程中断规则，interrupt这个动作， 必须发生在捕获(catch)该动作之前
    7. 对象销毁规则，初始化必须发生在finalize之前
    8. 线程终结规则，所有的操作都发生在线程死亡之前

    try{
        lock.lock() ;
    } finally{
        lock.unlock() ;
    }

    //------1
    synchronized (obj) {
        obj.wait();
    }
    //------2


6.volatile关键字的作用
    volatile 能保证 可见性 和 有序性，但不能保证原子性
    一旦一个共享变量被volatile修饰，具备两层语义
    1.保证了不同线程间的可见性
    2.禁止对其进行重排序，也就是保证了有序性
    3.并未保证原子性





原子性
    对基本数据类型的变量读取和赋值是保证了原子性的，
    要么都成功，要么都失败，这些操作不可被中断
    i=10;
        cache 10， memory 10

    a=10;   满足原子性
    b=a;    不满足，1.read a;2.assign b;
    c++;    不满足，1.read c;2.add;3.assign to c;
    c=c+1;  不满足，1.read c;2.add;3.assign to c;

    long 和 double 是 8 个字节，64位
        在 32 位的操作系统，需要分开赋值，因此不满足原子性

可见性
    使用volatile关键字保证可见性

有序性
    happens-before relationship




总结:
    1.cpu的大致结构
    2.JMM的大致结构
    3.缓存一致性协议
    4.并发编程的三大要素(原子性，可见性，有序性)
        有序性
            指令重排序
    5.happens-before规则
    6.volatile关键字的作用
 */
interface Z {
}
