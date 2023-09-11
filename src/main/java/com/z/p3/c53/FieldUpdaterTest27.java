package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class FieldUpdaterTest27 {

    static class Target {
        Integer i; // Must be volatile type
    }

    public static void main(String[] args) {
        Target target = new Target();
        AtomicReferenceFieldUpdater<Target, Integer> a = AtomicReferenceFieldUpdater.newUpdater(Target.class, Integer.class, "i");
        a.compareAndSet(target, 0, 1);
    }
}
