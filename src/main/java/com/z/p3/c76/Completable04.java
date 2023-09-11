package com.z.p3.c76;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Completable04 {

    /*
        一批任务，批量执行
            等到所有任务都完成 - 收集所有结果
     */
    public static void main(String[] args) throws InterruptedException {

        IntStream.range(0, 8)
                .boxed()
                .forEach(i ->
                        CompletableFuture.supplyAsync(Completable04::get)
                                .thenAccept(Completable04::display)
                                .whenComplete((v, t) -> System.out.println(i + " -> done"))
                );

        Thread.currentThread().join();
    }

    private static void display(int data) {
        System.out.println(Thread.currentThread().getName() + " display " + data);
    }

    private static int get() {
        String name = Thread.currentThread().getName();
        int value = ThreadLocalRandom.current().nextInt(3) + 1;
        try {
            System.out.println(name + " -> sleep: " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " -> done");
        return value;
    }
}
