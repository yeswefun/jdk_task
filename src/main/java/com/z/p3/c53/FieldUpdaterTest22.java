package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest22 {

    static class Target {
        volatile int i;
    }

    /*
        不能修改 private 修饰的字段
     */
    public static void main(String[] args) {
        Target target = new Target();
        AtomicIntegerFieldUpdater<Target> a = AtomicIntegerFieldUpdater.newUpdater(Target.class, "i");
        System.out.println(a.get(target));
        a.compareAndSet(target, 0, 1);
        System.out.println(a.get(target));
    }
}
