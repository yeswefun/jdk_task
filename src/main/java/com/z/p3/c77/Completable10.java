package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello")
//          .whenComplete((v, t) -> System.out.println(v));

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");

        future.whenComplete((v, t) -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--- over");
        });

        System.out.println("future: " + future.get());
    }
}
