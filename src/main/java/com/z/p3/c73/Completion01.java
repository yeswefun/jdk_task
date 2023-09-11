package com.z.p3.c73;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class Completion01 {

    /*
        完成任务之后，没有回调
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

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
                }
        );

        List<Future<Integer>> futures = executorService.invokeAll(callableList);

        //任务一: 1s
        //任务二: 1m, 刚好碰到这个
        //不能获取最先完成的任务结果
        Integer v1 = futures.get(0).get();
        System.out.println(v1);

        Integer v2 = futures.get(1).get();
        System.out.println(v2);

        executorService.shutdown();
    }

    private static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
