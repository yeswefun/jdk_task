package com.z.p2.c27_immutable;

import static com.z.util.IO.println;

final class Demo21ImmutableTest {

    private static class ImmutableTarget {

        private final String name;

        public ImmutableTarget(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }
    }

    private static class SyncTarget {

        private String name;

        public synchronized void setName(String name) {
            this.name = name;
        }

        @Override
        public synchronized String toString() {
            return "[" + name + "]";
        }
    }

    /*
        多线程
            1000000
                sync, ms: 187
                immutable, ms: 47
     */
    public static void main(String[] args) throws InterruptedException {
        test10();
        test11();
    }

    private static final int N = 1000000;

    private static void test(String s) {
    }

    private static void test10() throws InterruptedException {

        long start = System.currentTimeMillis();
        SyncTarget syncTarget = new SyncTarget();
        syncTarget.setName("Jerry");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(syncTarget.toString());
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(syncTarget.toString());
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(syncTarget.toString());
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        println("ms: " + (end - start));
    }

    private static void test11() throws InterruptedException {
        long start = System.currentTimeMillis();
        ImmutableTarget immutableTarget = new ImmutableTarget("Jerry");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(immutableTarget.toString());
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(immutableTarget.toString());
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    test(immutableTarget.toString());
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        println("ms: " + (end - start));
    }
}
