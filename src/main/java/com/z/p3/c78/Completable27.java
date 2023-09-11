package com.z.p3.c78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Completable27 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("1--- start");
            sleep(500);
            System.out.println("1--- end");
            return "hello";
        });

        sleep(1000); // comment
        boolean affected = future.complete("world"); // -> 缓存真实数据
        System.out.println(affected);

        System.out.println(future.get());

        System.out.println("2 --- start");
        System.out.println(future.get());
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
