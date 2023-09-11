package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class FieldUpdaterTest25 {

    static class Target {
        volatile int i; // 类型不匹配, int != Integer
    }

    public static void main(String[] args) {
        Target target = new Target();
        AtomicReferenceFieldUpdater<Target, Integer> a = AtomicReferenceFieldUpdater.newUpdater(Target.class, Integer.class, "i");
        a.compareAndSet(target, null, 1);
    }
}
