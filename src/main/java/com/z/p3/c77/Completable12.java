package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable12 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(String::length);

        System.out.println("future: " + future.get());

        Thread.currentThread().join();
    }
}
