package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class FieldUpdaterTest30 {

    private volatile int i;

    private AtomicIntegerFieldUpdater<FieldUpdaterTest30> updater = AtomicIntegerFieldUpdater.newUpdater(FieldUpdaterTest30.class, "i");

    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue); //target, expect, update
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        FieldUpdaterTest30 t = new FieldUpdaterTest30();

        System.out.println(t.get());
        t.update(10);
        System.out.println(t.get());
    }
}
