package com.z.p1.c00_phase_1;

/*
public class Thread
    implements Runnable {}

Thread
    1. Java应用程序的main函数是一个线程，是被JVM启动的时候调用，线程的名字叫main

    2. 实现一个线程，必须创建Thread实例，重写 run方法，并且调用start方法

    3. 在JVM启动后，实际上有多个线程，但是至少有一个非守护线程

    4. 当你调用一个线程start方法的时候，此时至少有两个线程，
       一个是创建线程的父线程，还有一个是调用run方法的线程

    5. 线程的生命周期分为new, runnable, running, blocked, terminated


    Thread#start
        一个线程返回到 Thread#start 的调用处，一个线程返回到 Thread#run 方法

        同一个Thread对象不能调用start两次

        模板方法
            存在固定逻辑
            final修饰方法禁止子类重写


可执行单元与线程控制分离 - Runnable

    创建线程的两种方式
        继承Thread类并重写run方法
        实现Runnable接口并重写run方法，并将其作为参数传给Thread构造

    public class Thread implements Runnable {

        public synchronized void start() {}

        //...

        @Override
        public void run() {
            if (target != null) {
                target.run();
            }
        }
    }

    策略设计模式



Thread的构造方法
    tid         long
    name        String
    target      Runnable
    g           ThreadGroup
    daemon      boolean
    priority    int
    stackSize   long

    contextClassLoader              ClassLoader
    inheritedAccessControlContext   AccessControlContext
    inheritableThreadLocals         ThreadLocal.ThreadLocalMap



JVM 内存模型
    线程共享
        方法区
        堆区
    线程私有
        虚拟机栈, stackSize
        本地方法栈
        程序计数器

    JVM创建一个线程执行main方法
        创建一个虚拟机栈，线程私有
        JVM会为每一个线程创建一个虚拟机栈

    进程是操作系统最小的资源分配单位
    线程是操作系统最小的运算调度单位

    操作系统为每个进程分配的内存是有限的
        stackSize 越大，可以创建的线程数量越少
        stackSize 越小，可以创建的线程数量越多

    Exception in thread "Thread-4059"
    java.lang.OutOfMemoryError: Java heap space



线程的生命周期
    NEW -> RUNNABLE -> RUNNING -> BLOCKED -> RUNNABLE -> TERMINATED



守护线程, daemon
    同一个线程组内的所有非守护线程结束，该线程组内的其它守护线程也会结束

    public final void setDaemon(boolean on)
    public static int activeCount()
    public static int enumerate(Thread tarray[])

    A(Client) ---> B(Server)
        发送心跳检测

    整个过程客户端只有 主线程 和 A线程
        假设 A 是 非守护线程,
            主线程结束还需要另行通知 A 停止
            主线程结束，A 的存在导致 JVM 不会退出
        假设 A 是 守护线程，
            主线程结束，A 也会结束

    问题
        主线程结束，子线程A是非守护线程，子线程A开启子线程B，B是守护线程，那么A结束，B会不会也结束?
            会
        主线程结束，子线程A是非守护线程，子线程A开启子线程B，B是非守护线程，那么A结束，B会不会也结束?
            不会



Thread的其它方法
    public long getId()
    public final String getName()
    public final int getPriority()
        Priority
            [1, 10]
            1 -> min
            5 -> norm
            10-> max

        优先级越大，只是表示获取得cpu时间片的概率大了而已
            具体还是要看操作系统内核的调度算法



Thread#join
    Thread#join在哪个线程执行，就会阻塞哪个线程

    public final void join()
        throws InterruptedException
    public final void join(long millis, int nanos)
        throws InterruptedException
    public final void join(long millis, int nanos)
        throws InterruptedException

    public final boolean isAlive()

    源码分析

    class ThreadJoin4 {
        //主线程开启一个守护线程作为http服务
        //    守护线程：会随组内最后一个非守护线程的结束而结束
        //        不占用端口，不占用资源
        //主线程结束(守护线程也会结束)
        //    前提: 线程组内所有非守护线程都结束，那么线程组内所有守护线程也会随着结束
        public static void main(String[] args) throws InterruptedException {
            //主线程在等待主线程结束
            Thread.currentThread().join();
        }
    }



Thread#interrupt
    public void interrupt()
    public boolean isInterrupted()
        创建线程方式 - 继承Thread类
    public static boolean interrupted()
        创建线程方式 - 实现Runnable接口

    interrupt
        sleep
        wait
        join



停止线程
    loop + flag
    Thread#interrupt
    Thread#interrupt + Thread#isInterrupted
    利用 线程组内所有非守护线程结束，线程组内的守护线程也会随着结束 这个原理



Synchronized
    同步代码块
    同步方法

    修饰 静态方法，锁对象为 当前类对象, Xxx.class
        在静态代码块中，开启新的线程锁住 Xxx.class

    修饰 非静态方法，锁对象为 当前对象, this
        在构造方法中，开启新的线程锁住 this

    为什么要使用 Object 来作为锁对象?
        private static final Object LOCK = new Object();
        private final Object LOCK = new Object();



死锁
    A持有B需要的锁，而B持有A需要的锁

    分析
        jps
        jstack PID

    死锁的解决
        超时重试
        ...



生产者与消费者模型
    Producer, Consumer

    同步
        传输带
            生产完了通知消费
            消费完了通知生产

    notify 随机唤醒一个
    notifyAll 唤醒全部
        线程被唤醒后，还要判断执行条件

    sleep 与 wait 的区别
        1. Thread#sleep, Object#wait
        2. sleep 不会释放对象锁(monitor), wait 会释放对象锁(monitor)并且添加到对象锁(monitor)的等待队列中
        3. sleep 不依赖于 monitor, 而 wait 依赖于 monitor
        4. sleep 不需要被唤醒，而 wait 需要被唤醒(wait(10)除外)
        注: monitor == lock



多线程情况下，任务个数聘任制
    最多同时运行线程数为 N
    需要执行的任务数为 M
    M > N
        如果正在运行的线程数量 小于 最大最多同时运行线程数
            添加到执行队列
        如果正在运行的线程数量 大于 最大最多同时运行线程数
            阻塞



Synchronized 的不足
    争抢锁时，
        争抢失败，阻塞
            阻塞的线程，还没有办法被打断
        争抢成功的线程需要先执行完, 其它线程才可以再抢锁

    问题: 线程A争抢到锁后，线程B争抢失败，因此阻塞
        线程B在阻塞状态下无法被中断

    解决: 自定义锁
        在wait中醒来之后，还需要争抢到锁之后，才会进行 while(条件) 的判断

        //只有当前持有锁的线程才有权限释放被占用的锁
        if (Thread.currentThread() != currentThread) {
            return;
        }

        //不允许外面修改，否则报错
        return Collections.unmodifiableCollection(list);

        r.lock();
        try {
            //...
        } finally {
            r.unlock();
        }



linux下钩子函数监听程序崩溃回调
    $ nohup java [-cp .] Demo &
    $ tail -f nohup.out

    jps
    kill PID
        有回调
    kill -9 PID
        没有回调

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.println("ooh! Error occured!");
        notifyAndRelease();
    }));

    public void test() {
        Arrays.asList(Thread.currentThread().getStackTrace())
                .stream()
                .filter(elem -> !elem.isNativeMethod())
                .forEach(elem -> Optional
                        .of(elem.getClassName() + "#" + elem.getMethodName() + ":" + elem.getLineNumber())
                        .ifPresent(System.out::println)
                );
    }
 */
interface Z00_Thread {
}
