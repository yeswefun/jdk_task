package com.z.p3.c68;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MainTest06 {

    /*
        jdk 1.8
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();

        //Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
        //System.out.println(Runtime.getRuntime().availableProcessors()); // 8, xeon e3 1245 v3

        List<Callable<String>> list = IntStream.range(0, 10)
                .boxed()
                .map(i -> (Callable<String>) () -> {
                    System.out.println("thread: " + Thread.currentThread().getName() + " -> task-" + i);
                    sleep(3000);
                    return "task-" + i;
                }).collect(Collectors.toList());

        executorService.invokeAll(list).stream().map(future -> {
            try {
                return future.get(); // block
            } catch (Exception e) {
                throw new RuntimeException(e); // unchecked exception
            }
        }).forEach(System.out::println);
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
