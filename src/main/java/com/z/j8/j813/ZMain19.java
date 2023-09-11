package com.z.j8.j813;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ZMain19 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(false);
                return thread;
            }
        });

        List<Integer> productIds = Arrays.asList(1, 2, 3, 4, 5);
        List<Long> list = productIds.stream()
                .map(i -> CompletableFuture.supplyAsync(
                        () -> queryProduction(i), executorService
                        )
                )
                .map(future -> future.thenApply(ZMain19::div))
                .map(CompletableFuture::join).collect(Collectors.toList());

        list.forEach(System.out::println);

        executorService.shutdown();
    }

    private static long queryProduction(Integer i) {
        return get();
    }

    private static long get() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    private static long div(long value) {
        System.out.println(Thread.currentThread() + " -> div: " + value);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value / 10;
    }
}
