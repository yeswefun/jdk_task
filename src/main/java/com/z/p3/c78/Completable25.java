package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable25 {

    public static void main(String[] args) throws InterruptedException {

        // A -> result -> B -> whenComplete
        CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(500);
            System.out.println("1--- end");
            return "hello";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("2--- start");
            sleep(300);
            System.out.println("2--- end");
            return s.length();
        }))
                .thenAccept(System.out::println);
//                .whenComplete((v, t) -> System.out.println(v));

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
