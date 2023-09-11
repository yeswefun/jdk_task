package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api07 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100); // 10
//                Thread.sleep(300); // 20
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        })
        .applyToEither(CompletableFuture.supplyAsync(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }),
                v -> v * 10
        )
        .thenAccept(System.out::println);

        Thread.sleep(1000L);
    }
}
