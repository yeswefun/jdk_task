package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api08 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return get();
        })
        .acceptEither(CompletableFuture.supplyAsync(
            () -> {
                System.out.println(Thread.currentThread().getName());
                return get();
            }),
            System.out::println
        );

        Thread.sleep(1000L);
    }

    private static long get() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
