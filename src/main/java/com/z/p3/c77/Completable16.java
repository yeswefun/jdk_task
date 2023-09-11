package com.z.p3.c77;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Completable16 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(System.out::println);
    }
}
