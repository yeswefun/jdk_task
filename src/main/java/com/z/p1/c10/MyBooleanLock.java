package com.z.p1.c10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.z.util.IO.println;

class MyBooleanLock implements MyLock {

    /*
        锁是否被占用
     */
    private boolean occupied;

    /*
        当前持有锁的线程
            只有当前持有锁的线程才有权限释放被占用的锁
     */
    private Thread currentThread;

    /*
        因抢不到锁而被阻塞的线程集合
     */
    private final Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public MyBooleanLock() {
        occupied = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (occupied) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
            //在wait中醒来之后，还需要争抢到锁之后，才会进行 while(occupied) 的判断
        }
        occupied = true;
        currentThread = Thread.currentThread();
        blockedThreadCollection.remove(currentThread);
        Optional.of("********************************* " + currentThread.getName() + " get lock")
                .ifPresent(System.out::println);
    }

    @Override
    public synchronized void unlock() {
        //只有当前持有锁的线程才有权限释放被占用的锁
        if (Thread.currentThread() != currentThread) {
            println(Thread.currentThread().getName() + " 没有权限释放被占用的锁");
            return;
        }
        //MyBooleanLock的实例this作为锁
        occupied = false;
        println(Thread.currentThread().getName() + " release lock(Monitor)");
        notifyAll();
    }

    @Override
    public Collection<Thread> getBlockedThreads() {
        //不允许外面修改，否则报错
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedThreadSize() {
        return blockedThreadCollection.size();
    }

    /*
        核心逻辑
     */
    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeoutException {
        if (mills <= 0) {
            lock();
            return;
        }
        long remaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (occupied) {
            if (remaining <= 0) {
                throw new TimeoutException("****************** timeout: " + Thread.currentThread().getName());
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            remaining = endTime - System.currentTimeMillis();
        }

        occupied = true;
        currentThread = Thread.currentThread();
        blockedThreadCollection.remove(currentThread);
        Optional.of(currentThread.getName() + " get lock - lock(mills)")
                .ifPresent(System.out::println);
    }
}
