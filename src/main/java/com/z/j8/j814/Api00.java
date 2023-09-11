package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api00 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));

        Thread.sleep(1000L);
    }
}
