package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Completable17 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        future.thenRunAsync(() -> System.out.println("end-1"));
        future.thenRun(() -> System.out.println("end-2"));
    }
}
