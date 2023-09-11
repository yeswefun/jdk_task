package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable31 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(300);
            System.out.println("1--- end");
            return "hello";
        });

        future.thenApply(s -> {
            System.out.println("2 --- start");
            sleep(200);
            System.out.println("2 --- end");
            Integer.parseInt(s);
            return s + " -> append";
        })
                .exceptionally(Throwable::getMessage).thenAccept(System.out::println);
//                .whenComplete((v, t) -> System.out.println(v));

        future.whenComplete((v, t) -> System.out.println(v));

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
