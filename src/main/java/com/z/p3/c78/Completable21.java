package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable21 {

    // 两个都会执行
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(500); // 200
            System.out.println("1--- end");
            return "hello";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("2--- start");
            sleep(500);
            System.out.println("2--- end");
            return "world";
        }), s -> System.out.println(s));

        Thread.currentThread().join();
    }

    private static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
