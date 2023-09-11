package com.z.p3.c53;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class ReferenceTest02 {

    private static AtomicReference<Integer> ref = new AtomicReference<>(100);

    /*
        解决ABA问题
            AtomicStampedReference

        问题: CAS是通过值比较的形式来判断是否是同一个值
     */
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    TimeUnit.SECONDS.sleep(1);

                    boolean b = ref.compareAndSet(100, 101);
                    System.out.println(name + " -> " + b);

                    b = ref.compareAndSet(101, 100);
                    System.out.println(name + " -> " + b);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    TimeUnit.SECONDS.sleep(2);
                    boolean b = ref.compareAndSet(100, 101);
                    System.out.println(name + " -> " + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
