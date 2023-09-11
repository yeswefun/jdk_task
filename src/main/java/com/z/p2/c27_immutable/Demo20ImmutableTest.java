package com.z.p2.c27_immutable;

import static com.z.util.IO.println;

final class Demo20ImmutableTest {

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
        单线程
            1000000
                sync, ms: 46
                immutable, ms: 16
     */
    public static void main(String[] args) throws InterruptedException {
        test00();
        test01();
    }

    private static final int N = 1000000;

    private static void test(String s) {
    }

    private static void test00() {
        long start = System.currentTimeMillis();
        SyncTarget syncTarget = new SyncTarget();
        syncTarget.setName("Jerry");
        for (int i = 0; i < N; i++) {
            test(syncTarget.toString());
        }
        long end = System.currentTimeMillis();
        println("ms: " + (end - start));
    }

    private static void test01() {
        long start = System.currentTimeMillis();
        ImmutableTarget immutableTarget = new ImmutableTarget("Jerry");
        for (int i = 0; i < N; i++) {
            test(immutableTarget.toString());
        }
        long end = System.currentTimeMillis();
        println("ms: " + (end - start));
    }
}
