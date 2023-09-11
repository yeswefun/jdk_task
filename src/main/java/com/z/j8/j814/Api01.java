package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api01 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(() -> System.out.println("finished!"));

        Thread.sleep(1000L);
    }
}
