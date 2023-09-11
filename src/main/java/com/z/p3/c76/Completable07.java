package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable07 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("o --- start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("o --- end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("--- over"));

        System.out.println("future --- start");
        future.get();
        System.out.println("future --- end");

        Thread.currentThread().join();
    }
}
