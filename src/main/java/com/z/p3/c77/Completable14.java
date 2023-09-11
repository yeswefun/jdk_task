package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Completable14 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new RuntimeException("haha");
            } else {
                return "hello";
            }
        })
        .handle((s, t) -> {
            if (t != null) {
                System.out.println(t.getMessage());
                return 0;
            } else {
                return s.length();
            }
        });

        System.out.println("future: " + future.get());

        Thread.currentThread().join();
    }
}
