package com.z.p1.c00_phase_1;

/*
ThreadGroup构造方法
    ThreadGroup(String name)
        Constructs a new thread group.

    ThreadGroup(ThreadGroup parent, String name)
        Creates a new thread group.


ThreadGroup方法
    String getName()
        Returns the name of this thread group.

    int getMaxPriority()
        Returns the maximum priority of this thread group.
    void setMaxPriority(int pri)
        Sets the maximum priority of the group.

    String toString()
        Returns a string representation of this Thread group.

    ThreadGroup getParent()
        Returns the parent of this thread group.

    boolean parentOf(ThreadGroup g)
        Tests if this thread group is either the thread group argument or one of its ancestor thread groups.

    void list()
        Prints information about this thread group to the standard output.

    int activeCount()
        Returns an estimate of the number of active threads in this thread group and its subgroups.
    int activeGroupCount()
        Returns an estimate of the number of active groups in this thread group and its subgroups.

    int enumerate(Thread[] list)
        Copies into the specified array every active thread in this thread group and its subgroups.
    int enumerate(Thread[] list, boolean recurse)
        Copies into the specified array every active thread in this thread group.

    int enumerate(ThreadGroup[] list)
        Copies into the specified array references to every active subgroup in this thread group and its subgroups.
    int enumerate(ThreadGroup[] list, boolean recurse)
        Copies into the specified array references to every active subgroup in this thread group.


    boolean isDaemon()
        Tests if this thread group is a daemon thread group.
    void setDaemon(boolean daemon)
        Changes the daemon status of this thread group.


    void interrupt()
        Interrupts all threads in this thread group.

    void destroy()
        Destroys this thread group and all of its subgroups

    boolean isDestroyed()
        Tests if this thread group has been destroyed.

    void uncaughtException(Thread t, Throwable e)
        Called by the Java Virtual Machine when a thread in this thread group stops
        because of an uncaught exception, and the thread does not have a specific
        Thread.UncaughtExceptionHandler installed.

    void checkAccess()
        Determines if the currently running thread has permission to modify this thread group.
 */
interface ZThreadGroup {
}
