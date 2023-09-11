package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable08 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.completedFuture("hello")
                .thenAccept(System.out::println);

        System.out.println(future.isDone());

        Thread.currentThread().join();
    }
}
