package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable20 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(200);
            System.out.println("1--- end");
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("2--- start");
            sleep(500);
            System.out.println("2--- end");
            return 1000;
        }), (s, i) -> System.out.println(s + " --- " + i));

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
