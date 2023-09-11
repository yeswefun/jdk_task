package com.z.p3.c75;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ComplexDemo2 {

    /*
        ExecutorCompletionService 并不是 ExecutorService 的子类
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(service);

        List<Runnable> taskList = IntStream.range(0, 5)
                .boxed()
                .map(ComplexDemo2::task)
                .collect(Collectors.toList());

        taskList.forEach(r -> {
            completionService.submit(Executors.callable(r));
        });

        //有可能碰到执行时长最长的任务 - 阻塞
        Future<?> future;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }
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
