package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable28 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(500);
            System.out.println("1--- end");
            return "hello";
        });

        System.out.println("2 --- start");
        System.out.println(future.join()); // 没有抛出异常
        System.out.println("2 --- end");

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
