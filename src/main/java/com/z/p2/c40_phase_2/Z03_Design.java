package com.z.p2.c40_phase_2;

/*
内容
    1.You may not know the singleton design pattern
    2.WaitSet in synchronized monitor
    3.Cpu&Cpu cache&Main Memory&Data Bus&Cache Line
    4.The volatile key word in deep
    5.Java Class L oader
    6.Observer to monitor the Thread lifecycle
    7.Single ThreadedIExecution design pattern
    8.Immutable design pattern
    9.Guarded Suspension design pattern
    10.Balking design pattern
    11.Producer-Consumer
    12.Read-Write Lock design pattern
    13.Thread-Per-Message Design Pattern
    14.Work Thread Design Pattern
    15.Future Design Pattern
    16.Two-Phase Termination Design Pattern
    17.The Thread-Specific Storage
    18.Active Object
    19.JMM-Java Memory Model



1.You may not know the singleton design pattern

    直接在静态字段赋值
        类初始化时，就会分配内存
        不能延迟分配内存

    在静态方法中实例化并返回
        并发 引起 线程问题安全

        在静态方法上加 synchronized
            锁的力度太大

        DCL: Double Check Lock
            指令重排序 引起 线程安全问题
            解决: DCL + volatile

    静态内部类
        Holder, 类的加载方式(6种主动加载方式)

    枚举实例

    单例需要注意的点
        构造函数
        clone
        序列化



6.Observer to monitor the Thread lifecycle

    向 被观察者 注册 观察者

    Observerable, Subject
        addObserver, 注意重复添加的问题
        removeObserver
        notifyXxx

    Observer
        onXxx

        BinaryObserver
        OctalObserver



8.Immutable design pattern

    An object is considered immutable if its state cannot change after it is constructed

    A Strategy for Defining Immutable Objects
        Don't provide "setter" methods
            methods that modify fields or objects referred to by fields.

        Make all fields final and private.

        Don't allow subclasses to override methods.
            The simplest way to do this is to declare the class as final.
            A more sophisticated approach is to make the constructor private
            and construct instances in factory methods.

        If the instance fields include references to mutable objects,
        don't allow those objects to be changed:
            Don't provide methods that modify the mutable objects.
            Don't share references to the mutable objects.
            Never store references to external, mutable objects passed to the constructor;
            if necessary, create copies, and store references to the copies.
            Similarly, create copies of your internal mutable objects
            when necessary to avoid returning the originals in your methods.

    不可变对象
        String, immutable
        基本类型的包装类
            Integer
            Long

        不可变对象 一定是 线程安全
            里面的任何属性或引用类型的属性都不能被修改
        可变对象 不一定是 线程不安全
            StringBuffer
                synchronized

    线程安全的类
        StringBuffer, StringBuilder 阅读文档注释
        StringBuffer 是一个线程安全的类，但不是一个不可变对象，synchronized
        StringBuilder 是一个线程不安全的类

        servlet 不是线程安全的

    String 加上 synchronized 的地方都是由于外面传入了一个可变对象
        需要确保传入的可变对象不能为 null
        public String(@NonNull StringBuffer buffer)
        public boolean contentEquals(@NonNull CharSequence cs)



15.Future Design Pattern

    调用后不会阻塞，而是马上返回
        Future          -> 未来的凭据
        FutureTask      -> 将调用逻辑进行隔离
        FutureService   -> 桥接 Future 和 FutureTask
            Future 不需要知道 FutureTask 的存在
            FutureTask 不需要知道 Future 的存在
            调用方只需要知道 FutureService

    增加钩子方法

    例子:
        蛋糕店订蛋糕




17.The Thread-Specific Storage

    ThreadLocal 源码分析

    ThreadLocal
        initValue
        set
        get

    多线程执行上下文
        每次在 action 中都要传入 context

        解决: context 是与 线程 绑定的

        线程池: 需要清理上一次请求的 Context



7.Single ThreadedExecution design pattern
    GUI

    Gate



13.Thread-Per-Message Design Pattern
    每个请求启动一个线程
    引入线程池，并正确关闭线程池



11.Producer-Consumer
    生产者 和 消费者 模型
        引入 队列 作为 生产者 与 消费者 速度不一致的缓冲

    生产者生产一个产品，并放到 Queue 中
    消费者取出 Queue 的一个产品，进行处理



14.Work Thread Design Pattern，将请求切换到另一个线程中执行
    TransportThread
        -> Request
        -> Channel#put

    Channel
        WokerPool
            WorkerThread
                -> Channel#take
                -> Request#execute



9.Guarded Suspension design pattern

    手上有东西要做，其它事情先放一放(queue)，
    等我做完之后再处理(queue里面的事件)

    生产者 与 消费者 模型



16.Two-Phase Termination Design Pattern
    分两阶段终止
        一种先执行完终止处理再终止线程的模式
            线程处理任务的过程中接收到终止请求，先完成当前任务，再进行终止线程



12.Read-Write Lock design pattern
    读写锁
        读共享
        写独占
        写优先


10.Balking design pattern



18.Active Object



CountDownLatch
    多个线程并发处理任务，统计耗时(以最后一个为准)

    CountDownLatch latch = new CountDownLatch(4);
    latch.countDown();  // notify
    latch.await();      // while(count != total) { wait(); }
 */
interface Z03_Design {
}
