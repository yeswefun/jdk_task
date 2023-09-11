package com.z.p3.c54;

import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UnsafeTest02 {

    interface Counter {
        void increment();

        long getCounter();
    }

    /*
        IssueCounter
            counter: 995089
            ms: 211

        Synchronized
            counter: 1000000
            ms: 215

        Lock
            counter: 1000000
            ms: 180

        Atomic
            counter: 1000000
            ms: 210

        Unsafe
            counter: 1000000
            ms: 208
     */
    static class CouterRunnable implements Runnable {

        private final Counter counter;

        private final int num;

        public CouterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(1000);

//        Counter counter = new IssueCounter();
//        Counter counter = new SyncCounter();
//        Counter counter = new LockCounter();
//        Counter counter = new AtomicCounter();
        Counter counter = new CASCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CouterRunnable(counter, 1000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();

        System.out.println("counter: " + counter.getCounter());
        System.out.println("ms: " + (end - start));
    }

    /*
        SharedData
     */
    static class IssueCounter implements Counter {

        private long counter;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncCounter implements Counter {

        private long counter;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter {

        private long counter;

        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter {

        private final AtomicLong counter = new AtomicLong(0);

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.longValue();
        }
    }

    static class CASCounter implements Counter {

        private volatile long counter;
        private Unsafe unsafe;
        private long offset;

        public CASCounter() throws Exception {
            unsafe = UnsafeUtil.getUnsafe();
            offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = this.counter;
            while (!unsafe.compareAndSwapLong(this, offset, current, current + 1)) {
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }
}
