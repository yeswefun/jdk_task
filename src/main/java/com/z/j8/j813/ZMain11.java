package com.z.j8.j813;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class ZMain11 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Double> cf = new CompletableFuture<>();

        new Thread(() -> {
            double v = get();
            cf.complete(v);
        }).start();

        System.out.println("--- main thread start");

        cf.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(System.out::println);
        });
        System.out.println("--- main thread end");
    }

    private static double get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }
}
