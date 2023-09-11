package com.z.p3.c62;

class StampedLockTest00 {

    /*
        ReentrantLock
            与 synchronized 对比
                synchronized 的阻塞无法中断
                synchronized 是一个关键字，无法对其扩展

        synchronized 权重锁
        ReentrantLock 自旋锁
            CAS
            Unsafe

        R W X
        W W X
        W R X
        R R O

        ReentrantReadWriteLock

        100 threads
            99 read lock
            1  write lock, 更新慢

            1(r), 1(r), 1(r), ...
                什么时候才能轮到 1(w)
     */
    public static void main(String[] args) {
        // 先搞定 ReentrantLock 和 Condition
    }
}
