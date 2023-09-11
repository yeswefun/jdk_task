package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest23 {

    static class Target {
        volatile int i;
    }

    /*
        AtomicIntegerFieldUpdater#compareAndSet
            obj 不能为 null
     */
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Target> a = AtomicIntegerFieldUpdater.newUpdater(Target.class, "i");
        a.compareAndSet(null, 0, 1);
    }
}
