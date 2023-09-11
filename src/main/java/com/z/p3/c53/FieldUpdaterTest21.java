package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest21 {

    static class Target {
        private volatile int i;
    }

    /*
        不能修改 private 修饰的字段
     */
    public static void main(String[] args) {
        Target target = new Target();
        AtomicIntegerFieldUpdater<Target> a = AtomicIntegerFieldUpdater.newUpdater(Target.class, "i");
        System.out.println(target.i);
        a.compareAndSet(target, 0, 1);
        System.out.println(target.i);

    }
}
