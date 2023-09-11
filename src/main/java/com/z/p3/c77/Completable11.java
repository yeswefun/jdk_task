package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable11 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello")
//          .whenComplete((v, t) -> System.out.println(v));

        //连起来，whenCompleteAsync 会先执行完才继续往下执行的
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");

        future.whenCompleteAsync((v, t) -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--- over");
        });

        System.out.println("future: " + future.get());

        Thread.currentThread().join();
    }
}
