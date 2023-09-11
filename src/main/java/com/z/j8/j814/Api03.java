package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api03 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i)) // Fork Join, 组合设计模式
                .thenAccept(System.out::println);

        Thread.sleep(1000L);
    }
}
