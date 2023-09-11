package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest24 {

    static class Target {
        volatile int i;
    }

    /*
        field 不存在
     */
    public static void main(String[] args) {
        Target target = new Target();
        AtomicIntegerFieldUpdater<Target> a = AtomicIntegerFieldUpdater.newUpdater(Target.class, "x");
        a.compareAndSet(target, 0, 1);
    }
}
