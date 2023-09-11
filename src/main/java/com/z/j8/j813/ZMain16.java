package com.z.j8.j813;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

class ZMain16 {

    /*
        Daemon == true
            Daemon -> false
     */
    public static void main(String[] args) throws InterruptedException {

//        AtomicBoolean done = new AtomicBoolean(false);

        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(false);
                return thread;
            }
        });

        System.out.println("--- main thread start");

        CompletableFuture.supplyAsync(ZMain16::get, executorService)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(System.out::println);
//                    done.set(true);
                });

        System.out.println("--- main thread end");

//        Thread.currentThread().join(); // 不会退出
//        while (!done.get()) {
//            Thread.sleep(100);
//        }
        executorService.shutdown();
    }

    private static long get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
