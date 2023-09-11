package com.z.p3.c73;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class Completion04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3); // 影响顺序 -> 2

        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(executorService);

        List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(1);
                    System.out.println("sleep 1");
                    return 1;
                },
                () -> {
                    sleep(3);
                    System.out.println("sleep 3");
                    return 3;
                },
                () -> {
                    sleep(2);
                    System.out.println("sleep 2");
                    return 2;
                }
        );

        for (Callable<Integer> callable : callableList) {
            service.submit(callable);
        }

        //take, 获取最先执行完成的结果
        Future<Integer> future = service.poll();
        System.out.println(future); // null
    }

    private static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
