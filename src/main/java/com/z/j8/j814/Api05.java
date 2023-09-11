package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api05 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
//                .thenCombine(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> r1 + r2)
//                .thenCombine(CompletableFuture.supplyAsync(() -> 2), Integer::sum)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                });

        Thread.sleep(1000L);
    }
}
