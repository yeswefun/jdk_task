package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class Completable01 {

    /*
        CompletableFuture 是 Future 和 ExecutorService 的结合体
            内置的 ExecutorService
            Future 是没有 ExecutorService 的相关方法
                即 Future 无法对线程进行管理 -> daemon == true
     */
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> {
            System.out.println("--- done");
        });

        System.out.println("main thread");

        Thread.sleep(1000);
    }
}
