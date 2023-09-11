package com.z.p3.c75;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ComplexDemo3 {

    /*
        ExecutorCompletionService 并不是 ExecutorService 的子类
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor(); // note
        ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(service);

        List<Runnable> taskList = IntStream.range(0, 5)
                .boxed()
                .map(ComplexDemo3::task)
                .collect(Collectors.toList());

        taskList.forEach(r -> {
            completionService.submit(Executors.callable(r));
        });

        TimeUnit.SECONDS.sleep(1);
        //20秒之后，获取还有哪些任务没有执行 - 可是 ExecutorCompletionService$QueueingFuture@4e50df2e
        List<Runnable> runnableList = service.shutdownNow();
        System.out.println(runnableList.size());
        System.out.println(runnableList);
    }

    private static Runnable task(int i) {
        return () -> {
            try {
                System.out.println("task: " + i);
                TimeUnit.SECONDS.sleep(1 + i * 2L);
                System.out.println("task: " + i + " ---> finished!");
            } catch (InterruptedException e) {
                System.out.println("task: " + i + " ---> interrupted!");
                //e.printStackTrace();
            }
        };
    }
}
