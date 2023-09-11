package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class FieldUpdaterTest26 {

    static class Target {
        volatile Integer i = 0; // 类型不匹配-fix
    }

    public static void main(String[] args) {
        Target target = new Target();
        AtomicReferenceFieldUpdater<Target, Integer> a = AtomicReferenceFieldUpdater.newUpdater(Target.class, Integer.class, "i");
        System.out.println(a.get(target));
        a.compareAndSet(target, 0, 1);
        System.out.println(a.get(target));
    }
}
