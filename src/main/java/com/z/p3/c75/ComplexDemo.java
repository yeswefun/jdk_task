package com.z.p3.c75;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ComplexDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);

        List<Runnable> taskList = IntStream.range(0, 5)
                .boxed()
                .map(ComplexDemo::task)
                .collect(Collectors.toList());

        List<Future<?>> futureList = new ArrayList<>();
        taskList.forEach( r -> {
            Future<?> future = service.submit(r);
            futureList.add(future);
        });

        //有可能碰到执行时长最长的任务 - 阻塞
        futureList.get(4).get();
        System.out.println("--- 4");
        futureList.get(3).get();
        System.out.println("--- 3");
        futureList.get(2).get();
        System.out.println("--- 2");
        futureList.get(1).get();
        System.out.println("--- 1");
        futureList.get(0).get();
        System.out.println("--- 0");
    }

    private static Runnable task(int i) {
        return () -> {
            try {
                System.out.println("task: " + i);
                TimeUnit.SECONDS.sleep(1 + i * 2L);
                System.out.println("task: " + i + " ---> finished!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
