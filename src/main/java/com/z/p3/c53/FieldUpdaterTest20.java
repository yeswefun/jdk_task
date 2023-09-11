package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest20 {

    static class Target {
        volatile int i;
    }

    public static void main(String[] args) {
        Target target = new Target();
        AtomicIntegerFieldUpdater<Target> a = AtomicIntegerFieldUpdater.newUpdater(Target.class, "i");

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        int value = a.getAndIncrement(target);
                        System.out.println(Thread.currentThread().getName() + " -> " + value);
                    }
                }
            }.start();
        }
    }
}
