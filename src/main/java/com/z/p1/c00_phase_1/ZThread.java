package com.z.p1.c00_phase_1;

/*
Thread构造方法
    Thread()
        Allocates a new Thread object.

    Thread(Runnable target)
        Allocates a new Thread object.
    Thread(Runnable target, String name)
        Allocates a new Thread object.
    Thread(String name)
        Allocates a new Thread object.

    Thread(ThreadGroup group, Runnable target)
        Allocates a new Thread object.
    Thread(ThreadGroup group, Runnable target, String name)
        Allocates a new Thread object so that it has target as its run object,
        has the specified name as its name, and belongs to the thread group referred to by group.
    Thread(ThreadGroup group, String name)
        Allocates a new Thread object.

    Thread(ThreadGroup group, Runnable target, String name, long stackSize)
        Allocates a new Thread object so that it has target as its run object,
        has the specified name as its name, and belongs to the thread group referred to by group,
        and has the specified stack size.

        注: ThreadGroup, stackSize

        ThreadGroup
            便于统一处理，例如 统一挂起，结束

        stackSize
            越小，可创建的线程越多
            越大，可创建的线程越少
            注: 操作系统分配给进程的内存资源是一定的



Thread方法
    public long getId()
        Returns the identifier of this Thread

    public final ThreadGroup getThreadGroup()
        Returns the thread group to which this thread belongs.

    public final String getName()
        Returns this thread's name.
    public final synchronized void setName(String name)
        Changes the name of this thread to be equal to the argument name

    public final int getPriority()
        Returns this thread's priority.
    public final void setPriority(int newPriority)
        Changes the priority of this thread.

        MIN_PRIORITY    0
        NORM_PRIORITY   5
        MAX_PRIORITY    10

    public String toString()
        Returns a string representation of this thread,
        including the thread's name, priority, and thread group.


    public void run()
        If this thread was constructed using a separate Runnable run object,
        then that Runnable object's run method is called;

    public synchronized void start()
        Causes this thread to begin execution;
        the Java Virtual Machine calls the run method of this thread.

    public static native Thread currentThread();

    public static native void sleep(long millis)
        throws InterruptedException;
    public static void sleep(long millis, int nanos)
        throws InterruptedException {}

        The thread does not lose ownership of any monitors


    public static int activeCount()
        Returns an estimate of the number of active threads
        in the current thread's thread group and its subgroups.

    public static int enumerate(Thread tarray[])
        Copies into the specified array every active thread
        in the current thread's thread group and its subgroups


    public final void setDaemon(boolean on)
        Marks this thread as either a daemon thread or a user thread

    public final boolean isDaemon()
        Tests if this thread is a daemon thread.



    public void interrupt()
        Interrupts this thread.

    public boolean isInterrupted()
        Tests whether this thread has been interrupted.
        创建线程方式 - 继承Thread类

    public static boolean interrupted()
        Tests whether the current thread has been interrupted
        创建线程方式 - 实现Runnable接口

    public final boolean isAlive()
        Tests if this thread is alive
        A thread is alive if it has been started and has not yet died.



    public final void join()
        throws InterruptedException
        Waits for this thread to die.
        等同于 join(0)

    public final void join(long millis)
        throws InterruptedException
        Waits at most millis milliseconds for this thread to die.
        A timeout of 0 means to wait forever.

    public final void join(long millis, int nanos)
        throws InterruptedException
        Waits at most millis milliseconds plus nanos nanoseconds for this thread to die.



    public State getState()
        returns the state of this thread.
        This method is designed for use in monitoring of the system state,
        not for synchronization control.

        static class Thread.State
            A thread state.



    public static Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler()
        Returns the default handler invoked
        when a thread abruptly terminates due to an uncaught exception.
        If the returned value is null, there is no default.

    public static void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
        Set the default handler invoked
        when a thread abruptly terminates due to an uncaught exception,
        and no other handler has been defined for that thread.

    public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler()
        Returns the handler invoked
        when this thread abruptly terminates due to an uncaught exception.
        If this thread has not had an uncaught exception handler explicitly set
        then this thread's ThreadGroup object is returned,
        unless this thread has terminated, in which case null is returned.

        public class ThreadGroup
            implements Thread.UncaughtExceptionHandler {}

        static interface Thread.UncaughtExceptionHandler
            Interface for handlers invoked when a Thread abruptly terminates due to an uncaught exception.



static void dumpStack()
StackTraceElement[] getStackTrace()
static Map<Thread,StackTraceElement[]> getAllStackTraces()

ClassLoader getContextClassLoader()
void setContextClassLoader(ClassLoader cl)


static boolean holdsLock(Object obj)

static void yield()

void checkAccess()

protected Object clone()
 */
interface ZThread {
}
