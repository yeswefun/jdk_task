package com.z.p3.c53;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

class ReferenceTest01 {

    private static AtomicStampedReference<Integer> ref = new AtomicStampedReference<>(100, 0);

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

                    // expectedValue, updateValue, expectedStamp, updateStamp
                    boolean b = ref.compareAndSet(100, 101, ref.getStamp(), ref.getStamp() + 1);
                    System.out.println(name + " -> " + b);

                    // expectedValue, updateValue, expectedStamp, updateStamp
                    b = ref.compareAndSet(101, 100, ref.getStamp(), ref.getStamp() + 1);
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

                    int stamp = ref.getStamp();
                    System.out.println("------------ before: " + stamp);         // 0

                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("------------ after: " + ref.getStamp()); // 2

                    boolean b = ref.compareAndSet(100, 101, stamp, stamp + 1);
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
