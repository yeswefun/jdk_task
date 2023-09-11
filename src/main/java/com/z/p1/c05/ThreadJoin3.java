package com.z.p1.c05;

import java.util.stream.IntStream;

class ThreadJoin3 {

    /*
        join阻塞的是当前线程
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println("---> t running");
                Thread.sleep(3_000);
                System.out.println("---> t done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        t.join(200); // 200ms之后, 不管t是否结束，开始执行主线程剩下的代码

        System.out.println("********************* execute main thread");
        IntStream.range(1, 10)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
