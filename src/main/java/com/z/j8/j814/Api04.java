package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api04 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
//                .thenCombine(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> r1 + r2)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2), Integer::sum)
                .thenAccept(System.out::println);

        Thread.sleep(1000L);
    }
}
