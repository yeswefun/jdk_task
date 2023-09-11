package com.z.j8.j814;

import java.util.concurrent.CompletableFuture;

class Api06 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        })
        .runAfterBoth(CompletableFuture.supplyAsync(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }),
                () -> System.out.println("done"));

        Thread.sleep(1000L);
    }
}
