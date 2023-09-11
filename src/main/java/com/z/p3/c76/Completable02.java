package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable02 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> {
            System.out.println("--- done");
        });

        System.out.println("main thread");

//        Thread.sleep(1000);
        Thread.currentThread().join();
    }
}
