package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable10 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("1 --- start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("1 --- end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println("1 --- over"))
                , CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("2 --- start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("2 --- end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + " --- over"))
        );

        Thread.currentThread().join();
    }
}
