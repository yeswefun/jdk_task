package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable06 {

    /*
        post {
            basic
            detail
        }

        insert basic
        insert detail

        submit
            insert basic
            insert detail
     */
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(Object::new).thenAcceptAsync(obj -> {
            try {
                System.out.println("o --- start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("o --- end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterBoth( // 两个 CompletableFuture 都执行完之后才输出 finished
                CompletableFuture.supplyAsync(() -> "hello").thenAcceptAsync(s -> {
                    try {
                        System.out.println("s --- start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("s --- end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }),
                () -> System.out.println("--- finished")
        );

        Thread.currentThread().join();
    }
}
