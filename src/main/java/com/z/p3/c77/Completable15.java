package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Completable15 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello").toCompletableFuture();

        System.out.println(future.get());
    }
}
